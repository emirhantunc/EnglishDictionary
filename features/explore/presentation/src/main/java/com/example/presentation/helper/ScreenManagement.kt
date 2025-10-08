package com.example.presentation.helper


sealed class ScreenManagement() {
    data object Folder : ScreenManagement()
    data class Word(val folderId: Int = 0) : ScreenManagement()
}