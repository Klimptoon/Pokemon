package com.example.pokemon.di

import com.example.pokemon.data.network.PokemonRepository
import com.example.pokemon.domain.GetPokemonUseCase
import com.example.pokemon.presentation.MainViewModelFactory
import dagger.Module
import dagger.Provides


@Module
class AppModule {
    @Provides
    fun provideMainViewModelFactory(
        pokemonRepository: PokemonRepository,
        getPokemonUseCase: GetPokemonUseCase,

    ): MainViewModelFactory {
        return MainViewModelFactory(
            getPokemonUseCase = getPokemonUseCase,
            pokemonRepository
        )
    }
}