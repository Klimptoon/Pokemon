package com.example.pokemon.di

import com.example.pokemon.data.network.PokeApi
import com.example.pokemon.data.network.PokemonRepository
import com.example.pokemon.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun providePokemonRepository(
        api: PokeApi
    ) = PokemonRepository(api = api)

    @Provides
    fun providesPokeApi(): PokeApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PokeApi::class.java)
    }
}