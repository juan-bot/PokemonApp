package com.example.pokeapi.data

import com.example.pokeapi.data.remote.RetrofitPokemon
import com.example.pokeapi.domain.model.Pokemon
import com.example.pokeapi.domain.model.ResultState
import com.example.pokeapi.domain.repository.PokeRepository
import okio.IOException


class PokemonRepositoryImpl : PokeRepository {

    override suspend fun getAllPokemon(onProgress: (Int) -> Unit): ResultState<List<Pokemon>> {
        val pokemonList = mutableListOf<Pokemon>()
        try {
            val response = RetrofitPokemon.api().getPokemonList(100,0)
            if(response.isSuccessful){
                val totalItem = response.body()?.results?.size
                for ((index, pokemonSummary) in response.body()?.results?.withIndex()!!) {
                    val pokemonDetail = RetrofitPokemon.api().getPokemonDetail(pokemonSummary.name)
                    pokemonDetail.body()?.let { pokemonList.add(it) }

                    val progress = ((index + 1) * 100) / totalItem!!
                    onProgress(progress)
                }
                return ResultState.Success(pokemonList)
            } else{
                return ResultState.Error(Exception("Error en la respuesta: ${response.code()}"))
            }

        }catch (e: IOException){
            return ResultState.Error(e)
        }catch (e: Exception) {
            return ResultState.Error(e)
        }
    }

    override suspend fun getPokemon(name: String): ResultState<Pokemon> {
        try {
            val response =  RetrofitPokemon.api().getPokemonDetail(name)
            if(response.isSuccessful){
                val pokemon = response.body()
                return if(pokemon != null){
                    ResultState.Success(pokemon)
                } else {
                    ResultState.Error(Exception("No se encontró el Pokémon"))
                }
            } else {
                return ResultState.Error(Exception("Error en la respuesta: ${response.code()}"))

            }
        }catch (e: IOException){
            return ResultState.Error(e)
        }catch (e: Exception) {
            return ResultState.Error(e)
        }
    }
}