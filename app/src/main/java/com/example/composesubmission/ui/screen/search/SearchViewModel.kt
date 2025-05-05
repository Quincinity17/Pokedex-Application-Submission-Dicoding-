package com.example.composesubmission.ui.screen.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesubmission.data.PokemonRepository
import com.example.composesubmission.model.BasedPokemon
import com.example.composesubmission.model.Pokemon
import com.example.composesubmission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel untuk layar pencarian Pokémon.
 * Bertanggung jawab untuk menangani pencarian dan pengelolaan status favorit.
 * @param repository Repository untuk mengambil data Pokémon.
 */
class SearchViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _searchResults = MutableStateFlow<UiState<List<Pokemon>>>(UiState.Loading)
    val searchResults: StateFlow<UiState<List<Pokemon>>> get() = _searchResults

    /**
     * Melakukan pencarian Pokémon berdasarkan query yang diberikan.
     * @param query Kata kunci pencarian.
     */
    fun searchPokemon(query: String) {
        viewModelScope.launch {
            repository.searchPokemon(query)
                .catch { exception ->
                    _searchResults.value = UiState.Error(exception.message.toString())
                }
                .collect { results ->
                    _searchResults.value = UiState.Success(results)
                }
        }
    }

    /**
     * Menambah atau menghapus Pokémon dari daftar favorit.
     * @param pokemon Objek Pokémon yang akan ditambahkan atau dihapus dari favorit.
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
        }
    }
}
