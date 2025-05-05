package com.example.composesubmission

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.composesubmission.ui.theme.ComposeSubmissionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengaktifkan tampilan edge-to-edge untuk desain UI yang lebih modern.
        enableEdgeToEdge()

        // Menetapkan UI aplikasi dengan tema yang telah ditentukan.
        setContent {
            ComposeSubmissionTheme {
                PokemonApp() // Menampilkan aplikasi utama.
            }
        }
    }
}
