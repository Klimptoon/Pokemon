package com.example.pokemon.di

import com.example.pokemon.data.network.PokemonRepository
import com.example.pokemon.domain.GetPokemonUseCase
import dagger.Module
import dagger.Provides


@Module
class DomainModule {


    @Provides
    fun provideGetPokemonUseCase(repository: PokemonRepository) : GetPokemonUseCase {
        return GetPokemonUseCase(repository )
    }
}