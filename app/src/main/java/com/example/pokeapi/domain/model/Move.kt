package com.example.pokeapi.domain.model

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)