package com.example.composesubmission.model

import com.dicoding.pokemonapp.model.Stat
import com.dicoding.pokemonapp.model.Type

/**
 * Kelas data yang merepresentasikan sebuah Pokémon.
 * Mengimplementasikan [BasedPokemon] dan menyediakan status favorit default.
 */
data class Pokemon(
    override val id: Int,
    override val name: String,
    override val imageUrl: Int,
    override val type: List<Type>,
    override val stats: List<Stat>,
    override val moves: List<String>,
    override val prevEvoPokemon: Int,
    override val pastvEvoPokemon: Int,
    override val isFavorite: Boolean = false
) : BasedPokemon