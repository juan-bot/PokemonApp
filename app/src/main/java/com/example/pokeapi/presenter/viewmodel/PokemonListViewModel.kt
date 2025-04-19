package com.example.pokeapi.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.PokemonRepositoryImpl
import com.example.pokeapi.domain.model.Pokemon
import com.example.pokeapi.domain.usecase.GetPokemonListUseCase
import com.example.pokeapi.presenter.ui.PokemonAdapter
import kotlinx.coroutines.launch


class PokemonListViewModel: ViewModel(){

    private val _pokeList = MutableLiveData<List<Pokemon>>()
    val pokelist: LiveData<List<Pokemon>> = _pokeList

    private val repository = PokemonRepositoryImpl()
    private val getPokemonListUseCase = GetPokemonListUseCase(repository)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _progressValue = MutableLiveData<Int>()
    val progressValue: LiveData<Int> get() = _progressValue

    fun loadPoke(){

        viewModelScope.launch {
            try {
                _isLoading.value = true
                _progressValue.value = 0
                val result = getPokemonListUseCase.execute{ percent ->
                    _progressValue.value = percent
                    Log.d("PROGRESS",percent.toString())
                }
                _pokeList.postValue(result)
                _isLoading.value = false
            }catch(_: Exception){

            }finally {
                _isLoading.value = false
            }
        }
    }

}