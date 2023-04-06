package com.example.pokemon.data.network.responses

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)