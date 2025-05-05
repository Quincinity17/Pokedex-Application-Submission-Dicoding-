package com.example.composesubmission.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class yang merepresentasikan item navigasi dalam aplikasi.
 * @param title Nama atau label navigasi.
 * @param icon Ikon yang digunakan dalam navigasi.
 * @param screen Objek screen yang terkait dengan navigasi ini.
 */
data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)
