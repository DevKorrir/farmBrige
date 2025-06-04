package dev.korryr.farmbrige.ui.features.home.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Home Screen",
            modifier = modifier
        )
    }

}