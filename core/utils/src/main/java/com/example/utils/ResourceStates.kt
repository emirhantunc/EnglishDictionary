package com.example.utils

sealed class ResourceStates{
    data object Loading : ResourceStates()
    data object Success : ResourceStates()
    data class Error(val message: String) : ResourceStates()
    data object Initial : ResourceStates()
}