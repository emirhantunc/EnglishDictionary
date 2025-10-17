package com.example.presentation.helper

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun TextStyle.Companion.modified() = TextStyle(
    color = Color.Black,
    fontSize = 14.sp,
    fontWeight = FontWeight.Black
)