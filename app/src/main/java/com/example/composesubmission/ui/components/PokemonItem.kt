package com.example.composesubmission.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.pokemonapp.model.Type
import com.example.composesubmission.model.BasedPokemon
import com.example.composesubmission.R
import com.example.composesubmission.model.Pokemon
import com.example.composesubmission.ui.theme.ComposeSubmissionTheme

/**
 * Komponen UI untuk menampilkan item Pokémon dalam daftar.
 *
 * @param pokemon Data Pokémon yang akan ditampilkan.
 * @param isFavorite Status favorit Pokémon.
 * @param onFavoriteClick Callback yang dipanggil saat ikon favorit diklik.
 * @param modifier Modifier untuk menyesuaikan tampilan.
 */
@Composable
fun PokemonItem(
    pokemon: BasedPokemon,
    isFavorite: Boolean,
    onFavoriteClick: (BasedPokemon) -> Unit,
    modifier: Modifier = Modifier
) {
    var favoriteState by remember { mutableStateOf(isFavorite) }

    // Gradient background berdasarkan tipe Pokémon
    val backgroundBrush = when (pokemon.type.firstOrNull()?.name) {
        "Grass" -> Brush.verticalGradient(listOf(Color(0xFF78C850), Color(0xFF4E8234)))
        "Fire" -> Brush.verticalGradient(listOf(Color(0xFFF08030), Color(0xFFC03028)))
        "Water" -> Brush.verticalGradient(listOf(Color(0xFF6890F0), Color(0xFF1E90FF)))
        "Bug" -> Brush.verticalGradient(listOf(Color(0xFFA8B820), Color(0xFF6D7815)))
        "Poison" -> Brush.verticalGradient(listOf(Color(0xFFA040A0), Color(0xFF6A006A)))
        "Flying" -> Brush.verticalGradient(listOf(Color(0xFFA890F0), Color(0xFF705898)))
        "Electric" -> Brush.verticalGradient(listOf(Color(0xFFF8D030), Color(0xFFE0C000)))
        "Normal" -> Brush.verticalGradient(listOf(Color(0xFFA8A878), Color(0xFF6D6D4E)))
        else -> Brush.verticalGradient(listOf(Color.Gray, Color.DarkGray))
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundBrush)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // ID Pokémon
        Text(
            text = "#${pokemon.id.toString().padStart(3, '0')}",
            fontSize = 14.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Gambar Pokémon
        androidx.compose.foundation.Image(
            painter = painterResource(pokemon.imageUrl),
            contentDescription = pokemon.name,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            // Nama Pokémon
            Text(
                text = pokemon.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            // Badge Tipe Pokémon
            Row {
                pokemon.type.forEach { type ->
                    val typeColor = when (type.name) {
                        "Grass" -> Color(0xFF1A8827)
                        "Fire" -> Color(0xFFF00E2F)
                        "Water" -> Color(0xFF2153FC)
                        "Bug" -> Color(0xFF1ED14C)
                        "Poison" -> Color(0xFF7D40A7)
                        "Flying" -> Color(0xFF8BBCDF)
                        "Electric" -> Color(0xFFF89C08)
                        "Normal" -> Color(0xFF732A0D)
                        else -> Color.Gray
                    }

                    Box(
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .clip(RoundedCornerShape(50))
                            .background(typeColor)
                            .padding(horizontal = 8.dp, vertical = 0.dp)
                    ) {
                        Text(
                            text = type.name,
                            fontSize = 12.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Icon Favorite
        Icon(
            painter = painterResource(R.drawable.ic_favorite_filled),
            contentDescription = stringResource(R.string.favorite),
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    favoriteState = !favoriteState
                    onFavoriteClick(pokemon)
                },
            tint = if (favoriteState) Color.Red else Color.White
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PokemonItemPreview() {
    ComposeSubmissionTheme {
        PokemonItem(
            pokemon = Pokemon(
                id = 1,
                name = "Bulbasaur",
                imageUrl = R.drawable.img_bulbasaur,
                type = listOf(Type("Grass", "#78C850"), Type("Poison", "#A040A0")),
                stats = listOf(),
                moves = listOf(),
                prevEvoPokemon = 0,
                pastvEvoPokemon = 0
            ),
            isFavorite = false,
            onFavoriteClick = {},
            modifier = Modifier.padding(8.dp)
        )
    }
}