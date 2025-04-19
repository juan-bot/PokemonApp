package com.example.pokeapi.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.PokemonRepositoryImpl
import com.example.pokeapi.domain.model.Pokemon
import com.example.pokeapi.domain.model.ResultState
import com.example.pokeapi.domain.usecase.GetPokemonUseCase
import kotlinx.coroutines.launch

class PokemonDetailViewModel: ViewModel() {
    private val _pokemon = MutableLiveData<Pokemon>()
    val pokemon: MutableLiveData<Pokemon> = _pokemon
    private val repository = PokemonRepositoryImpl()
    private val getPokemonUseCase = GetPokemonUseCase(repository)
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    fun loadPoke(name: String){
        viewModelScope.launch {
            try {
                when(val result = getPokemonUseCase.execute(name)){
                    is ResultState.Success -> {
                        _pokemon.value = result.data
                    }
                    is ResultState.Error -> {
                        _error.value = "Error al cargar los datos ${result.exception}"
                    }
                }
            }catch (e: Exception){
                _error.value = "Error al cargar los datos"
                Log.e("PokemonViewModel", "Error al cargar el Pokémon", e)
            }
        }
    }
}