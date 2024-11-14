package com.example.myapplication.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun FooterBranding(modifier: Modifier,emojiString: String) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Bottom) {
        Text(
            text = "MADE IN $emojiString BY ROGIR COMPANY"
        )
    }
}
