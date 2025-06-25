package dev.korryr.farmbrige.ui.features.home.model

data class TaskItem(
    val name: String,
    val dueDate: String,
    val isUrgent: Boolean
)