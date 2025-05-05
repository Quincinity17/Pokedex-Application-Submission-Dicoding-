package com.example.composesubmission.ui.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.pokemonapp.model.Type
import com.example.composesubmission.R
import com.example.composesubmission.data.PokemonRepository
import com.example.composesubmission.di.Injection
import com.example.composesubmission.model.BasedPokemon
import com.example.composesubmission.model.Pokemon
import com.example.composesubmission.ui.ViewModelFactory
import com.example.composesubmission.ui.common.UiState
import com.example.composesubmission.ui.components.PokemonItem
import com.example.composesubmission.ui.components.SearchBar
import com.example.composesubmission.ui.theme.ComposeSubmissionTheme

/**
 * Screen utama yang menampilkan daftar Pokémon dan fitur pencarian.
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (Long) -> Unit,
    navigateToSearch: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getAllPokemon()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD93939))
    ) {
        Text(
            text = "POKEDEX",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onSearch = { navigateToSearch(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .padding(8.dp)
        ) {
            Column {
                Text(
                    text = "Semua Pokémon",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    modifier = Modifier.padding(start = 18.dp, top = 12.dp, bottom = 12.dp)
                )

                when (uiState) {
                    is UiState.Loading -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }
                    is UiState.Success -> {
                        HomeContent(
                            pokemonList = (uiState as UiState.Success<List<Pokemon>>).data,
                            onFavoriteClick = { viewModel.toggleFavorite(it) },
                            navigateToDetail = navigateToDetail
                        )
                    }
                    is UiState.Error -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(text = "Error: ${(uiState as UiState.Error).errorMessage}")
                        }
                    }
                }
            }
        }
    }
}

/**
 * Menampilkan daftar Pokémon dalam bentuk LazyColumn.
 */
@Composable
fun HomeContent(
    pokemonList: List<Pokemon>,
    modifier: Modifier = Modifier,
    onFavoriteClick: (BasedPokemon) -> Unit,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(pokemonList) { pokemon ->
            PokemonItem(
                pokemon = pokemon,
                isFavorite = PokemonRepository.getInstance().isPokemonFavorite(pokemon.id),
                onFavoriteClick = onFavoriteClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        Log.d("PokemonClick", "ID: ${pokemon.id}")
                        navigateToDetail(pokemon.id.toLong())
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ComposeSubmissionTheme {
        HomeScreen(
            navigateToDetail = {},
            navigateToSearch = {}
        )
    }
}
