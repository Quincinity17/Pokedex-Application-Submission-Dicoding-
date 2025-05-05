package com.example.composesubmission.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composesubmission.data.PokemonRepository
import com.example.composesubmission.model.Pokemon
import com.example.composesubmission.ui.screen.detail.DetailViewModel
import com.example.composesubmission.ui.screen.favorite.FavoriteViewModel
import com.example.composesubmission.ui.screen.home.HomeViewModel
import com.example.composesubmission.ui.screen.search.SearchViewModel

/**
 * Factory class untuk membuat instance ViewModel dengan parameter repository.
 * Digunakan untuk menghindari kode boilerplate saat menyediakan ViewModel dalam Composable.
 *
 * @param repository Repository yang digunakan untuk menyediakan data dalam ViewModel.
 */
class ViewModelFactory(private val repository: PokemonRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository) as T
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel(repository) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
