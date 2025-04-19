package com.example.pokeapi.domain.repository

import com.example.pokeapi.domain.model.PokeResponse
import com.example.pokeapi.domain.model.Pokemon

interface PokeRepository {
    suspend fun getAllPokemon(onProgress: (Int) -> Unit): List<Pokemon>
    suspend fun getPokemon(name: String): Pokemon
}