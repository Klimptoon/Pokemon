package com.example.pokemon.presentation

import androidx.lifecycle.ViewModel
import com.example.pokemon.data.network.PokemonRepository
import javax.inject.Inject

class PokemonInfoViewModel (
    private val repository: PokemonRepository
    ) : ViewModel() {
}