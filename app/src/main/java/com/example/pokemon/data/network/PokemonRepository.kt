package com.example.pokemon.data.network

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pokemon.data.network.responses.Pokemon
import com.example.pokemon.data.network.responses.PokemonList
import com.example.pokemon.util.Constants.PAGE_SIZE
import com.example.pokemon.util.Resourse
import java.lang.Exception
import javax.inject.Inject

class PokemonRepository  (
    private val api: PokeApi,
) {
    fun getPokemons() = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, maxSize = 60),
        pagingSourceFactory = {PokemonListPageSource(api)}
    ).liveData


    suspend fun getPokemonInfo(pokemonName: String) : Resourse<Pokemon> {
        val response = try {
            api.getPokemonInfo(name = pokemonName)
        } catch (e: Exception) {
            return Resourse.Error("Error message")
        }
        return Resourse.Success(response)
    }

}