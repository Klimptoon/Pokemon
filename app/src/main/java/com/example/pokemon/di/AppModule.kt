package com.example.pokemon.di

import com.example.pokemon.data.network.PokemonRepository
import com.example.pokemon.presentation.MainViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class AppModule {
    @Provides
    fun provideMainViewModelFactory(
        pokemonRepository: PokemonRepository,
    ): MainViewModelFactory {
        return MainViewModelFactory(
            pokemonRepository = pokemonRepository
        )
    }
}