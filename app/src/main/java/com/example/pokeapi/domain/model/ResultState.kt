package com.example.pokeapi.domain.model

open class ResultState<out T> {
    data class Success<out T>(val data: T): ResultState<T>()
    data class Error(val exception: Throwable): ResultState<Nothing>()
}