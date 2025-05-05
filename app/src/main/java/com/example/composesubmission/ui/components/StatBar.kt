package com.example.composesubmission.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.pokemonapp.model.Stat
import com.example.composesubmission.ui.theme.ComposeSubmissionTheme


@Composable
fun StatBar(
    statName: String,
    statValue: Int,
    maxValue: Int = 100,
    barColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = statName,
            fontSize = 14.sp,
            modifier = Modifier.width(60.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f) // Atur lebar max bar
                .height(8.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(4.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(statValue / maxValue.toFloat())
                    .height(8.dp)
                    .background(barColor, shape = RoundedCornerShape(4.dp))
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        
        Text(
            text = statValue.toString(),
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StatBarPreview() {
    ComposeSubmissionTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            StatBar(statName = "HP", statValue = 35, barColor = Color.Red)
            StatBar(statName = "Attack", statValue = 55, barColor = Color.Red)
            StatBar(statName = "Defense", statValue = 40, barColor = Color.Red)
            StatBar(statName = "Sp. Atk", statValue = 50, barColor = Color.Red)
            StatBar(statName = "Sp. Def", statValue = 50, barColor = Color.Red)
            StatBar(statName = "Speed", statValue = 90, barColor = Color.Red)
        }
    }
}