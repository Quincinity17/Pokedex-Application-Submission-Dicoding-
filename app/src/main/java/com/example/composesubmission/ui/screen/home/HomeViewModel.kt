package com.example.composesubmission.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesubmission.data.PokemonRepository
import com.example.composesubmission.model.BasedPokemon
import com.example.composesubmission.model.FavoritePokemon
import com.example.composesubmission.model.Pokemon
import com.example.composesubmission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * ViewModel untuk layar Home, mengelola data daftar Pokémon dan status favorit.
 * @param repository Repository yang digunakan untuk mengambil data Pokémon.
 */
class HomeViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    // State UI yang menyimpan daftar Pokémon
    private val _uiState: MutableStateFlow<UiState<List<Pokemon>>> = MutableStateFlow(UiState.Loading)

    // Expose UI state sebagai StateFlow agar dapat diamati dari UI
    val uiState: StateFlow<UiState<List<Pokemon>>> get() = _uiState

    /**
     * Mengambil semua Pokémon dari repository dan memperbarui UI state.
     */
    fun getAllPokemon() {
        viewModelScope.launch {
            repository.getAllPokemon()
                .catch { exception ->
                    _uiState.value = UiState.Error(exception.message.toString())
                }
                .collect { pokemonList ->
                    _uiState.value = UiState.Success(pokemonList)
                }
        }
    }

    /**
     * Menambah atau menghapus Pokémon dari daftar favorit.
     * @param pokemon Objek Pokémon yang akan diubah status favoritnya.
     */
    fun toggleFavorite(pokemon: BasedPokemon) {
        viewModelScope.launch {
            Log.d("FavoriteToggle", "${pokemon.name} dipilih untuk perubahan status favorit.")
            if (repository.isPokemonFavorite(pokemon.id)) {
                Log.d("FavoriteToggle", "${pokemon.name} akan dihapus dari favorit.")
                repository.removePokemonFromFavorite(pokemon.id)
            } else {
                Log.d("FavoriteToggle", "${pokemon.name} akan ditambahkan ke favorit.")
                repository.addPokemonToFavorite(pokemon)
            }
            // Memperbarui daftar Pokémon setelah perubahan favorit
            getAllPokemon()
        }
    }
}
