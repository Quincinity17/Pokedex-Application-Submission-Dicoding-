package com.example.composesubmission.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composesubmission.data.PokemonRepository
import com.example.composesubmission.model.BasedPokemon
import com.example.composesubmission.model.FavoritePokemon
import com.example.composesubmission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<FavoritePokemon>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<FavoritePokemon>>> get() = _uiState

    init {
        getAllFavPokemon()
    }

    fun getAllFavPokemon() {
        viewModelScope.launch {
            repository.getAllFavoritePokemon()
                .catch { _uiState.value = UiState.Error(it.message.toString()) }
                .collect { pokemonList ->
                    _uiState.value = UiState.Success(pokemonList)
                }
        }
    }

    fun toggleFavorite(pokemon: BasedPokemon) {
        viewModelScope.launch {
            if (repository.isPokemonFavorite(pokemon.id)) {
                repository.removePokemonFromFavorite(pokemon.id)
            } else {
                repository.addPokemonToFavorite(pokemon)
            }
            getAllFavPokemon()
        }
    }
}

