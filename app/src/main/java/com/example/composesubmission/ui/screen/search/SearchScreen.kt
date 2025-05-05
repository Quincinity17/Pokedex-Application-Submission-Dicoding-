package com.example.composesubmission.ui.screen.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composesubmission.R
import com.example.composesubmission.data.PokemonRepository
import com.example.composesubmission.di.Injection
import com.example.composesubmission.model.BasedPokemon
import com.example.composesubmission.model.Pokemon
import com.example.composesubmission.ui.ViewModelFactory
import com.example.composesubmission.ui.common.UiState
import com.example.composesubmission.ui.components.PokemonItem
import com.example.composesubmission.ui.theme.ComposeSubmissionTheme

/**
 * Screen untuk menampilkan hasil pencarian Pokémon.
 * @param query Kata kunci pencarian.
 * @param viewModel ViewModel untuk mengelola logika pencarian.
 * @param navigateToDetail Fungsi navigasi ke layar detail.
 * @param navigateBack Fungsi untuk kembali ke layar sebelumnya.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    query: String,
    viewModel: SearchViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (Long) -> Unit,
    navigateBack: () -> Unit,
) {
    val uiState by viewModel.searchResults.collectAsState()

    // Memulai pencarian saat query berubah
    LaunchedEffect(query) {
        viewModel.searchPokemon(query)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Search Results") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            when (uiState) {
                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is UiState.Success -> {
                    val pokemonList = (uiState as UiState.Success<List<Pokemon>>).data
                    if (pokemonList.isEmpty()) {
                        EmptySearchResult(query)
                    } else {
                        SearchContent(
                            pokemonList = pokemonList,
                            onFavoriteClick = { viewModel.toggleFavorite(it) },
                            navigateToDetail = navigateToDetail
                        )
                    }
                }
                is UiState.Error -> {
                    Text(text = "Error: ${(uiState as UiState.Error).errorMessage}")
                }
            }
        }
    }
}

/**
 * Menampilkan daftar hasil pencarian Pokémon.
 */
@Composable
fun SearchContent(
    pokemonList: List<Pokemon>,
    modifier: Modifier = Modifier,
    onFavoriteClick: (BasedPokemon) -> Unit,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxHeight()
    ) {
        items(pokemonList) { pokemon ->
            PokemonItem(
                pokemon = pokemon,
                isFavorite = PokemonRepository.getInstance().isPokemonFavorite(pokemon.id),
                onFavoriteClick = onFavoriteClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigateToDetail(pokemon.id.toLong()) }
            )
        }
    }
}

/**
 * Menampilkan tampilan ketika hasil pencarian kosong.
 */
@Composable
fun EmptySearchResult(query: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_empty),
            contentDescription = "No Pokémon Found",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Pokémon \"$query\" tidak ditemukan",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Lagi ngumpet kali, coba aja cari yang lain",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    ComposeSubmissionTheme {
        SearchScreen(query = "Bulbasaur", navigateToDetail = {}, navigateBack = {})
    }
}
