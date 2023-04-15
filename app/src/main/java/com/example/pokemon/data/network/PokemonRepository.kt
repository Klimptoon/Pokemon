package com.example.pokemon.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pokemon.data.network.responses.Pokemon
import com.example.pokemon.util.Constants.ERROR_MESSAGE
import com.example.pokemon.util.Constants.MAX_SIZE
import com.example.pokemon.util.Constants.PAGE_SIZE
import com.example.pokemon.util.Resource
import java.lang.Exception

class PokemonRepository(private val api: PokeApi,) {
    fun getPokemons() = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, maxSize = MAX_SIZE),
        pagingSourceFactory = { PokemonListPageSource(api) }
    ).liveData

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(name = pokemonName)
        } catch (e: Exception) {
            return Resource.Error(ERROR_MESSAGE)
        }
        return Resource.Success(response)
    }
}
