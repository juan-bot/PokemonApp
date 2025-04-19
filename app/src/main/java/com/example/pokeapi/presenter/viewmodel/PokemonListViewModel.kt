package com.example.pokeapi.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.PokemonRepositoryImpl
import com.example.pokeapi.domain.model.PokeResponse
import com.example.pokeapi.domain.model.Pokemon
import com.example.pokeapi.domain.usecase.GetPokemonListUseCase
import com.example.pokeapi.presenter.ui.PokemonAdapter
import kotlinx.coroutines.launch


class PokemonListViewModel: ViewModel(){

    lateinit var pokemonList: List<Pokemon>
    var adpList: MutableLiveData<PokemonAdapter> = MutableLiveData()
    private lateinit var adapter : PokemonAdapter
    val repository = PokemonRepositoryImpl()
    val getPokemonListUseCase = GetPokemonListUseCase(repository)
    fun loadPoke(){

        viewModelScope.launch {
            try {
                pokemonList = getPokemonListUseCase.execute()
                adapter = PokemonAdapter(pokemonList)
                adpList.postValue(adapter)
            }catch(_: Exception){

            }
        }
    }

}