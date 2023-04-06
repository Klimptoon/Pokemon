package com.example.pokemon.data.network.responses

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)