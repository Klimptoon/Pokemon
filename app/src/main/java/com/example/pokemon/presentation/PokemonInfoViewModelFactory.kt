package com.example.pokemon.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.domain.GetPokemonUseCase
import com.example.pokemon.util.Constants
import javax.inject.Inject

class PokemonInfoViewModelFactory @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
) : ViewModelProvider.Factory {
    @Suppress(Constants.UNCHECKED)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokemonInfoViewModel(
            getPokemonUseCase = getPokemonUseCase
        ) as T
    }
}