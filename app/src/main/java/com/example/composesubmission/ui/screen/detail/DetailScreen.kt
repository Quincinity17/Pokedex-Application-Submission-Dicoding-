package com.example.composesubmission.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composesubmission.model.Pokemon
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.composesubmission.ui.theme.ComposeSubmissionTheme
import com.dicoding.pokemonapp.model.Stat
import com.dicoding.pokemonapp.model.Type
import com.example.composesubmission.R
import com.example.composesubmission.ui.components.StatBar
/**
 * Screen untuk menampilkan detail informasi Pokémon.
 * @param pokemon Objek Pokémon yang akan ditampilkan.
 * @param onBack Fungsi untuk kembali ke layar sebelumnya.
 * @param onFavoriteClick Fungsi untuk menambahkan atau menghapus Pokémon dari favorit.
 */
@Composable
fun DetailScreen(
    pokemon: Pokemon,
    onBack: () -> Unit,
    onFavoriteClick: () -> Unit
) {
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

    Column(modifier = Modifier.fillMaxSize()) {
        // Header dengan gambar Pokémon dan tombol kembali
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(backgroundBrush)
                .padding(16.dp)
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Image(
                painter = painterResource(id = pokemon.imageUrl),
                contentDescription = pokemon.name,
                modifier = Modifier.size(150.dp).align(Alignment.BottomCenter)
            )
        }

        // Informasi Pokémon
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White, shape = RoundedCornerShape(topStart = 48.dp, topEnd = 24.dp))
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = pokemon.name, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.height(12.dp))

                PokemonStatsSummary(pokemon)

                Column(modifier = Modifier.padding(16.dp)) {
                    pokemon.stats.forEach { stat ->
                        StatBar(
                            statName = stat.name,
                            statValue = stat.value,
                            barColor = when (stat.name) {
                                "HP" -> Color(0xFF4CAF50)
                                "Attack" -> Color(0xFFF44336)
                                "Defense" -> Color(0xFFFF9800)
                                "Speed" -> Color(0xFF2196F3)
                                else -> Color.Gray
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }
}

/**
 * Menampilkan ringkasan statistik Pokémon.
 */
@Composable
fun PokemonStatsSummary(pokemon: Pokemon) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        listOf(
            "HP" to pokemon.stats[0].value,
            "Attack" to pokemon.stats[1].value,
            "Defense" to pokemon.stats[2].value
        ).forEach { (label, value) ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = value.toString(), fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4285F4))
                Text(text = label, fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

/**
 * Preview untuk DetailScreen.
 */
@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    ComposeSubmissionTheme {
        DetailScreen(
            pokemon = Pokemon(
                id = 9,
                name = "Blastoise",
                imageUrl = R.drawable.img_pidgey,
                type = listOf(Type("Water", "#6890F0")),
                stats = listOf(
                    Stat("HP", 85),
                    Stat("Attack", 83),
                    Stat("Defense", 100),
                    Stat("Speed", 78)
                ),
                moves = listOf("Hydro Pump", "Bite", "Ice Beam"),
                prevEvoPokemon = 8,
                pastvEvoPokemon = 7
            ),
            onBack = {},
            onFavoriteClick = {}
        )
    }
}
