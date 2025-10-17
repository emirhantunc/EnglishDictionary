package com.example.englishdictionary.ui

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomItems(
    val icon: ImageVector,
    val name: Int,
    val onClick: () -> Unit,
    val bottomItem: BottomItem
)