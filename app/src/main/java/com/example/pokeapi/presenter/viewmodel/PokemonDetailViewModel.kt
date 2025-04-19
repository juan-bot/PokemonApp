package com.example.pokeapi.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.PokemonRepositoryImpl
import com.example.pokeapi.domain.model.Pokemon
import com.example.pokeapi.domain.usecase.GetPokemonUseCase
import kotlinx.coroutines.launch

class PokemonDetailViewModel: ViewModel() {
    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: MutableLiveData<Pokemon> = _pokemon
    private val repository = PokemonRepositoryImpl()
    private val getPokemonUseCase = GetPokemonUseCase(repository)
    fun loadPoke(name: String){
        viewModelScope.launch {
            try {
                val result = getPokemonUseCase.execute(name)
                _pokemon.postValue(result)
            }catch (_: Exception){

            }
        }
    }
}