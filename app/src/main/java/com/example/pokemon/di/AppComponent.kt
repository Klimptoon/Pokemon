package com.example.pokemon.di

import com.example.pokemon.presentation.MainActivity
import com.example.pokemon.presentation.MainFragment
import dagger.Component


@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(mainFragment: MainFragment)
}