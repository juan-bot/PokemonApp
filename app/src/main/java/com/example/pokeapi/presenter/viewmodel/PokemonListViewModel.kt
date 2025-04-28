package com.example.pokeapi.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.PokemonRepositoryImpl
import com.example.pokeapi.domain.model.Pokemon
import com.example.pokeapi.domain.model.ResultState
import com.example.pokeapi.domain.usecase.GetPokemonListUseCase
import com.example.pokeapi.domain.usecase.GetPokemonUseCase
import kotlinx.coroutines.launch


class PokemonListViewModel: ViewModel(){

    private val _pokeList = MutableLiveData<List<Pokemon>>()
    val pokelist: LiveData<List<Pokemon>> = _pokeList

    private val repository = PokemonRepositoryImpl()
    private val getPokemonListUseCase = GetPokemonListUseCase(repository)
    private val getPokemonUseCase = GetPokemonUseCase(repository)
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _progressValue = MutableLiveData<Int>()
    val progressValue: LiveData<Int> get() = _progressValue

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error
    fun loadPoke(){
        viewModelScope.launch {
            _pokeList.value = emptyList()
            try {
                _isLoading.value = true
                _progressValue.value = 0
                val result = getPokemonListUseCase.execute{ percent ->
                    _progressValue.value = percent
                }
                when (result){
                    is ResultState.Success -> {
                        _pokeList.value = result.data
                    }
                    is ResultState.Error -> {
                        _error.value = "Error al cargar los pokemones ${result.exception.message.toString()}"
                    }
                }
                _isLoading.value = false
            }catch(e: Exception){
                _error.value = "Error al cargar los pokemones"
                Log.e("PokemonViewModel", "Error al cargar los pokemones", e)
            }finally {
                _isLoading.value = false
            }
        }
    }
    fun searchPoke(name: String){
        if(name.isNotEmpty())
            viewModelScope.launch {
                _pokeList.value = emptyList()
                try {
                    when (val result = getPokemonUseCase.execute(name)){
                        is ResultState.Success -> {
                            _pokeList.value = listOf(result.data)
                        }
                    }
                }catch(e: Exception){
                    _error.value = "Error al encontrar el pokemon"
                    _pokeList.value = emptyList()
                    Log.e("PokemonViewModel", "Error al cargar los pokemones", e)
                }finally {
                    Log.e("PokemonViewModel", "Error al cargar los pokemones")
                }

            }

    }
}