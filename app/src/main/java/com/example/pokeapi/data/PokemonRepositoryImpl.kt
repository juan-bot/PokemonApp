package com.example.pokeapi.data

import com.example.pokeapi.data.remote.RetrofitPokemon
import com.example.pokeapi.domain.model.Pokemon
import com.example.pokeapi.domain.repository.PokeRepository



class PokemonRepositoryImpl : PokeRepository {

    override suspend fun getAllPokemon(): List<Pokemon> {
        val response = RetrofitPokemon.api().getPokemonList(2,0)
        return response.results.map { it ->
            RetrofitPokemon.api().getPokemonDetail(it.name)

        }
    }

    override suspend fun getPokemon(name: String): Pokemon {
        return RetrofitPokemon.api().getPokemonDetail(name)

    }


}