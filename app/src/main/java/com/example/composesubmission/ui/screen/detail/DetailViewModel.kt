package com.example.composesubmission.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesubmission.data.PokemonRepository
import com.example.composesubmission.ui.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Any>>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<Any>>>
        get() = _uiState

    fun getPokemonById(source: String){
        viewModelScope.launch {
            val dataFlow: Flow<List<Any>> = when (source) {
                "Home" -> repository.getAllPokemon()
                    .map { pokemonList ->
                        pokemonList.sortedBy { it.id } // Urutkan berdasarkan ID Pokémon
                    } as Flow<List<Any>>

                "Favorite" -> repository.getAllFavoritePokemon()
                    .map { favoriteList ->
                        favoriteList.sortedBy { it.id } // Urutkan berdasarkan ID Favorite Pokémon
                    } as Flow<List<Any>>

                else -> flowOf(emptyList()) // Jika source tidak valid, kembalikan list kosong
            }

            dataFlow
                .catch { _uiState.value = UiState.Error(it.message.toString()) }
                .collect { pokemonList ->
                    _uiState.value = UiState.Success(pokemonList)
                }
        }
    }
}
