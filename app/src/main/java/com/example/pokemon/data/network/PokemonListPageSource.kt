package com.example.pokemon.data.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemon.data.network.PokeApi
import com.example.pokemon.data.network.responses.PokemonList
import com.example.pokemon.data.network.responses.Result
import com.example.pokemon.util.Constants.PAGE_SIZE
import java.lang.Exception

class PokemonListPageSource(
    private val pokeApi: PokeApi,

    ) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null

        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val pageIndex = params.key ?: 0

        return try {
            val pokemons = pokeApi.getPokemonList(PAGE_SIZE, pageIndex * PAGE_SIZE).results
            Log.d("ff", "list " +  pokemons)


            return LoadResult.Page(
                data = pokemons,

                prevKey = if(pageIndex == 0) null else pageIndex - 1,

                nextKey = if(pokemons.size < PAGE_SIZE) null else pageIndex + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }

    }

}