package com.example.pokeapi.domain.repository

import com.example.pokeapi.domain.model.Pokemon
import com.example.pokeapi.domain.model.ResultState

interface PokeRepository {
    suspend fun getAllPokemon(onProgress: (Int) -> Unit): ResultState<List<Pokemon>>
    suspend fun getPokemon(name: String): ResultState<Pokemon>
}