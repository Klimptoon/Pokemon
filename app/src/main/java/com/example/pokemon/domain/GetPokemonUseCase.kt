package com.example.pokemon.domain

import com.example.pokemon.data.network.PokemonRepository
import com.example.pokemon.data.network.responses.Pokemon
import com.example.pokemon.util.Resource

class GetPokemonUseCase(private val repository: PokemonRepository) {

    suspend fun getPokemon(name: String): Resource<Pokemon> {
        return repository.getPokemonInfo(name)
    }
}
