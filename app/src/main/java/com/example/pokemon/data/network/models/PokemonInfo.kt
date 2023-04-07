package com.example.pokemon.data.network.models

data class PokemonInfo(
    val pokemonName: String,
    val number: Int,
    val weight : Int,
    val height: Int,
    val image: String,
    val type: MutableList<String>,
)
