package com.example.pokeapi.domain.usecase

import com.example.pokeapi.domain.model.Pokemon
import com.example.pokeapi.domain.repository.PokeRepository

class GetPokemonUseCase(
    private val repository: PokeRepository
) {
    suspend fun execute(name: String): Pokemon {
        return repository.getPokemon(name)
    }
}