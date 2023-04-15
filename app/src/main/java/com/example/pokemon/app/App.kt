package com.example.pokemon.app

import android.app.Application
import com.example.pokemon.di.AppComponent
import com.example.pokemon.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
