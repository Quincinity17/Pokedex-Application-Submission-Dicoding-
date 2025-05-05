package com.example.composesubmission.model

import com.dicoding.pokemonapp.model.Stat
import com.dicoding.pokemonapp.model.Type

/**
 * Interface yang merepresentasikan dasar dari sebuah Pokémon.
 * Digunakan untuk menyediakan struktur umum yang dapat diimplementasikan oleh kelas lain.
 */
interface BasedPokemon {
    val id: Int               // ID unik Pokémon
    val name: String          // Nama Pokémon
    val imageUrl: Int         // Resource ID gambar Pokémon (drawable)
    val type: List<Type>      // Daftar tipe Pokémon
    val stats: List<Stat>     // Statistik Pokémon (HP, Attack, Defense, dll.)
    val moves: List<String>   // Daftar gerakan Pokémon
    val prevEvoPokemon: Int   // ID Pokémon evolusi sebelumnya
    val pastvEvoPokemon: Int  // ID Pokémon evolusi berikutnya
    val isFavorite: Boolean   // Status favorit Pokémon
}