package com.example.pokeapi.domain.usecase

import com.example.pokeapi.domain.model.Pokemon
import com.example.pokeapi.domain.repository.PokeRepository


class GetPokemonListUseCase (
    private val repository: PokeRepository
){
    suspend fun execute(): List<Pokemon> {
        return repository.getAllPokemon()
    }

}