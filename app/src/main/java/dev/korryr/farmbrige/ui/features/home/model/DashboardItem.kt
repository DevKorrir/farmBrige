package dev.korryr.farmbrige.ui.features.home.model

import androidx.compose.ui.graphics.Color

data class DashboardItem(
    val title: String,
    val value: String,
    val color: Color,
    val description: String
)