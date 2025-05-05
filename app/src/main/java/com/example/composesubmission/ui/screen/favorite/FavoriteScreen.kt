package com.example.composesubmission.ui.screen.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composesubmission.R
import com.example.composesubmission.di.Injection
import com.example.composesubmission.model.BasedPokemon
import com.example.composesubmission.model.FavoritePokemon
import com.example.composesubmission.ui.ViewModelFactory
import com.example.composesubmission.ui.common.UiState
import com.example.composesubmission.ui.components.PokemonItem
import com.example.composesubmission.ui.theme.ComposeSubmissionTheme

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is UiState.Loading -> {
            viewModel.getAllFavPokemon()
        }
        is UiState.Success -> {
            val pokemonList = (uiState as UiState.Success<List<FavoritePokemon>>).data
            if (pokemonList.isEmpty()) {
                EmptyFavoriteState()
            } else {
                FavContent(
                    pokemonList = pokemonList,
                    modifier = modifier,
                    onFavoriteClick = { viewModel.toggleFavorite(it) },
                    navigateToDetail = navigateToDetail
                )
            }
        }
        is UiState.Error -> {
            Text("Error: ${(uiState as UiState.Error).errorMessage}")
        }
    }
}

@Composable
fun EmptyFavoriteState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_empty),
            contentDescription = "No Favorite Pokémon",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Yah sepi ga ada Pokémon",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Favoritin Pokémon lain dong biar ga sepi",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun FavContent(
    pokemonList: List<FavoritePokemon>,
    modifier: Modifier = Modifier,
    onFavoriteClick: (BasedPokemon) -> Unit,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(pokemonList, key = { it.id }) { pokemon ->
            PokemonItem(
                pokemon = pokemon,
                isFavorite = true,
                onFavoriteClick = onFavoriteClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navigateToDetail(pokemon.id.toLong()) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteScreenPreview() {
    ComposeSubmissionTheme {
        FavoriteScreen(navigateToDetail = {})
    }
}
