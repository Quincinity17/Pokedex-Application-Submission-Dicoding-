package com.example.composesubmission.ui.navigation

/**
 * Sealed class yang merepresentasikan layar dalam navigasi aplikasi.
 * @param route Rute yang digunakan untuk menavigasi ke layar tertentu.
 */
sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Favorite : Screen("favorite")
    data object Profile : Screen("profile")

    object Search : Screen("search/{query}") {
        fun createRoute(query: String) = "search/$query"
    }

    /**
     * Layar detail reward yang memerlukan ID reward sebagai parameter.
     */
    data object DetailPokemon : Screen("home/{pokemonId}") {
        fun createRoute(rewardId: Long) = "home/$rewardId"
    }
}