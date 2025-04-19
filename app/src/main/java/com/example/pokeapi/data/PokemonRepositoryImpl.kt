package com.example.pokeapi.data

import com.example.pokeapi.data.remote.RetrofitPokemon
import com.example.pokeapi.domain.model.Pokemon
import com.example.pokeapi.domain.repository.PokeRepository



class PokemonRepositoryImpl : PokeRepository {

    override suspend fun getAllPokemon(onProgress: (Int) -> Unit): List<Pokemon> {
        val pokemonList = mutableListOf<Pokemon>()
        val response = RetrofitPokemon.api().getPokemonList(100,0)
        val totalItem = response.results.size
        for ((index, pokemonSummary) in response.results.withIndex()) {
            val pokemonDetail = RetrofitPokemon.api().getPokemonDetail(pokemonSummary.name)
            pokemonList.add(pokemonDetail)

            val progress = ((index + 1) * 100) / totalItem
            onProgress(progress)
        }

        return pokemonList

    }

    override suspend fun getPokemon(name: String): Pokemon {
        return RetrofitPokemon.api().getPokemonDetail(name)

    }


}