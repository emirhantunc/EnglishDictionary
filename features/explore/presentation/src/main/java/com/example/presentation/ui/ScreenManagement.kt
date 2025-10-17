package com.example.presentation.ui

sealed class ScreenManagement() {
    class Word() : ScreenManagement(){
        companion object {
            fun createRoute(folderId: Int): String = "word/$folderId"
        }
    }
}