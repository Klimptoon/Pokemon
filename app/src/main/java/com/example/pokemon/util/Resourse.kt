package com.example.pokemon.util

sealed class Resourse<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resourse<T>(data = data,)
    class Error<T>(message: String, data: T? = null) : Resourse<T>(data = data, message = message,)
}
