package com.example.composesubmission

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.composesubmission.ui.navigation.Screen
import com.example.composesubmission.ui.navigation.NavigationItem
import com.example.composesubmission.ui.screen.detail.DetailScreen
import com.example.composesubmission.ui.screen.favorite.FavoriteScreen
import com.example.composesubmission.ui.screen.home.HomeScreen
import com.example.composesubmission.ui.screen.profile.ProfileScreen
import com.example.composesubmission.ui.screen.search.SearchScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.navArgument
import com.example.composesubmission.data.PokemonRepository

@Composable
fun PokemonApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // BottomBar hanya ditampilkan jika bukan di layar Detail atau Search
            if (currentRoute !in listOf(Screen.DetailPokemon.route, Screen.Search.route)) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Navigasi ke HomeScreen
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { pokemonId ->
                        navController.navigate(Screen.DetailPokemon.createRoute(pokemonId))
                    },
                    navigateToSearch = { query ->
                        navController.navigate(Screen.Search.createRoute(query))
                    }
                )
            }

            // Navigasi ke SearchScreen
            composable(
                route = Screen.Search.route,
                arguments = listOf(navArgument("query") { type = NavType.StringType })
            ) { backStackEntry ->
                val query = backStackEntry.arguments?.getString("query") ?: ""
                SearchScreen(
                    query = query,
                    navigateToDetail = { pokemonId ->
                        navController.navigate(Screen.DetailPokemon.createRoute(pokemonId))
                    },
                    navigateBack = { navController.navigateUp() } // Tombol Back
                )
            }

            // Navigasi ke FavoriteScreen
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { pokemonId ->
                        navController.navigate(Screen.DetailPokemon.createRoute(pokemonId))
                    }
                )
            }

            // Navigasi ke ProfileScreen
            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            // Navigasi ke DetailScreen
            composable(
                route = Screen.DetailPokemon.route,
                arguments = listOf(navArgument("pokemonId") { type = NavType.LongType })
            ) { backStackEntry ->
                val pokemonId = backStackEntry.arguments?.getLong("pokemonId") ?: -1L
                val pokemon = PokemonRepository.getInstance().getPokemonById(pokemonId)

                if (pokemon != null) {
                    DetailScreen(
                        pokemon = pokemon,
                        onBack = { navController.navigateUp() },
                        onFavoriteClick = {}
                    )
                } else {
                    Text("Pokémon not found")
                }
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem("Home", Icons.Default.Home, Screen.Home),
            NavigationItem("Favorite", Icons.Default.Favorite, Screen.Favorite),
            NavigationItem("Profile", Icons.Default.AccountCircle, Screen.Profile)
        )

        navigationItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Red,
                    unselectedIconColor = Color(0xFFE0E0E0),
                    selectedTextColor = Color.Red,
                    unselectedTextColor = Color(0xFFE0E0E0),
                    indicatorColor = Color(0xFFFFD0E1)
                ),
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    val navController = rememberNavController()
    BottomBar(navController = navController)
}
