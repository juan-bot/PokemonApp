package com.example.pokeapi.domain.model

data class Abilities(
    val is_hidden: Boolean,
    val slot: Int,
    val ability: Ability
)
data class Ability(
    val name: String,
    val url: String
)