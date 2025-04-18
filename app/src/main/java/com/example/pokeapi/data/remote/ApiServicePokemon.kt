package com.example.pokeapi.data.remote

import com.example.pokeapi.domain.model.PokeResponse
import com.example.pokeapi.domain.model.Pokemon
import com.example.pokeapi.domain.model.ResultState
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServicePokemon {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Response<PokeResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") id: String): Response<Pokemon>
}
