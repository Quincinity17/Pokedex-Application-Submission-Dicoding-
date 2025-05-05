package com.example.composesubmission.di

import com.example.composesubmission.data.PokemonRepository

object Injection {

    /**
     * Menyediakan instance dari `PokemonRepository`.
     *
     * @return PokemonRepository - Instance dari PokemonRepository.
     */
    fun provideRepository(): PokemonRepository {
        return PokemonRepository.getInstance()
    }
}