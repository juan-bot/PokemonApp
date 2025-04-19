package com.example.pokeapi.domain.repository

import com.example.pokeapi.domain.model.PokeResponse
import com.example.pokeapi.domain.model.Pokemon

interface PokeRepository {
    suspend fun getAllPokemon(): List<Pokemon>
    suspend fun getPokemon(): Pokemon
}