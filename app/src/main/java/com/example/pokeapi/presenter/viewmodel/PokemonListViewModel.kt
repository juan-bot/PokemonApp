package com.example.pokeapi.presenter.viewmodel

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

    fun loadPoke(){

        viewModelScope.launch {
            try {
                val result = getPokemonListUseCase.execute()
                _pokeList.postValue(result)

            }catch(_: Exception){

            }
        }
    }

}