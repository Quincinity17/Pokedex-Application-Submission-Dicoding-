package com.example.composesubmission.data

import android.util.Log
import com.example.composesubmission.model.BasedPokemon
import com.example.composesubmission.model.DummyPokemonData
import com.example.composesubmission.model.FavoritePokemon
import com.example.composesubmission.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf

class PokemonRepository {

    // Menggunakan MutableList untuk menyimpan data Pokémon dan Pokémon favorit
    private val pokemonList = mutableListOf<Pokemon>()
    private val _pokemonFavoriteList = MutableStateFlow<List<FavoritePokemon>>(emptyList())
    val pokemonFavoriteList = _pokemonFavoriteList.asStateFlow()

    init {
        // Mengisi data awal dari DummyPokemonData jika list kosong
        if (pokemonList.isEmpty()) {
            pokemonList.addAll(DummyPokemonData.dummyPokemonList)
            Log.d("PokemonRepository", "Data Pokémon berhasil diinisialisasi.")
        }
    }

    /**
     * Mengembalikan semua Pokémon dalam bentuk Flow.
     *
     * @return Flow<List<Pokemon>> - Flow yang berisi daftar semua Pokémon.
     */
    fun getAllPokemon(): Flow<List<Pokemon>> {
        return flowOf(pokemonList)
    }

    /**
     * Mengembalikan semua Pokémon favorit dalam bentuk Flow.
     *
     * @return Flow<List<FavoritePokemon>> - Flow yang berisi daftar semua Pokémon favorit.
     */
    fun getAllFavoritePokemon(): Flow<List<FavoritePokemon>> {
        return pokemonFavoriteList
    }

    /**
     * Menambahkan Pokémon ke daftar favorit.
     *
     * @param pokemon - Pokémon yang akan ditambahkan ke favorit.
     */
    fun addPokemonToFavorite(pokemon: BasedPokemon) {
        if (_pokemonFavoriteList.value.none { it.id == pokemon.id }) {
            val newList = _pokemonFavoriteList.value.toMutableList().apply {
                add(
                    FavoritePokemon(
                        id = pokemon.id,
                        name = pokemon.name,
                        imageUrl = pokemon.imageUrl,
                        type = pokemon.type,
                        stats = pokemon.stats,
                        moves = pokemon.moves,
                        prevEvoPokemon = pokemon.prevEvoPokemon,
                        pastvEvoPokemon = pokemon.pastvEvoPokemon
                    )
                )
            }
            _pokemonFavoriteList.value = newList // Update state agar UI berubah
            Log.d("PokemonRepository", "${pokemon.name} berhasil ditambahkan ke favorit.")
        }
    }


    /**
     * Menghapus Pokémon dari daftar favorit berdasarkan ID.
     *
     * @param pokemonId - ID Pokémon yang akan dihapus dari favorit.
     */
    fun removePokemonFromFavorite(pokemonId: Int) {
        val newList = _pokemonFavoriteList.value.filter { it.id != pokemonId }
        if (newList.size != _pokemonFavoriteList.value.size) {
            _pokemonFavoriteList.value = newList // Update state agar UI berubah
            Log.d(
                "PokemonRepository",
                "Pokémon dengan ID $pokemonId berhasil dihapus dari favorit."
            )
        } else {
            Log.d(
                "PokemonRepository",
                "Pokémon dengan ID $pokemonId tidak ditemukan di daftar favorit."
            )
        }
    }

    /**
     * Mengecek apakah Pokémon ada di daftar favorit berdasarkan ID.
     *
     * @param pokemonId - ID Pokémon yang akan dicek.
     * @return Boolean - True jika Pokémon ada di favorit, False jika tidak.
     */
    fun isPokemonFavorite(pokemonId: Int): Boolean {
        return _pokemonFavoriteList.value.any { it.id == pokemonId }
    }

    /**
     * Mengambil data Pokémon berdasarkan ID.
     *
     * @param pokemonId - ID Pokémon yang akan dicari.
     * @return Pokemon? - Objek Pokémon jika ditemukan, null jika tidak.
     */
    fun getPokemonById(pokemonId: Number): Pokemon? {
        Log.d("PokemonRepository", "Mencari Pokémon dengan ID: $pokemonId")

        val result = pokemonList.find { it.id.toLong() == pokemonId.toLong() }

        if (result != null) {
            Log.d("PokemonRepository", "Pokémon ditemukan: ${result.name}")
        } else {
            Log.d("PokemonRepository", "Pokémon dengan ID $pokemonId tidak ditemukan.")
        }

        return result
    }

    /**
     * Mencari Pokémon berdasarkan nama (untuk fitur pencarian).
     *
     * @param query - Kata kunci pencarian.
     * @return Flow<List<Pokemon>> - Flow yang berisi daftar Pokémon yang sesuai dengan query.
     */
    fun searchPokemon(query: String): Flow<List<Pokemon>> {
        Log.d("PokemonRepository", "Mencari Pokémon dengan query: $query")
        return flowOf(pokemonList.filter { it.name.contains(query, ignoreCase = true) })
    }

    companion object {
        @Volatile
        private var instance: PokemonRepository? = null

        /**
         * Mendapatkan instance singleton dari PokemonRepository.
         *
         * @return PokemonRepository - Instance dari PokemonRepository.
         */
        fun getInstance(): PokemonRepository =
            instance ?: synchronized(this) {
                instance ?: PokemonRepository().also { instance = it }
            }
    }
}