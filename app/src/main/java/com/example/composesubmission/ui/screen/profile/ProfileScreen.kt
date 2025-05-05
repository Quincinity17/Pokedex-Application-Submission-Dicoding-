package com.example.composesubmission.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composesubmission.R
import com.example.composesubmission.ui.theme.ComposeSubmissionTheme

@Composable
fun ProfileScreen(
    profileImage: Int = R.drawable.img_foto_diri_resized, // Ganti dengan resource foto profil
    name: String = "Muhammad Faza Lamik",
    email: String = "170902Faza@gmail.com"
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Foto Profil
        Image(
            painter = painterResource(id = profileImage),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Nama Saya
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall
        )

        // Email Saya
        Text(
            text = email,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

/**
 * Preview untuk ProfileScreen.
 */
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ComposeSubmissionTheme {
        ProfileScreen()
    }
}
