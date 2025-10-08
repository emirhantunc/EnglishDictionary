package com.example.englishdictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.englishdictionary.ui.DictionaryApp
import com.example.englishdictionary.ui.hideSystemBars
import com.example.englishdictionary.ui.theme.EnglishDictionaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EnglishDictionaryTheme {
                DictionaryApp()
            }
        }
        hideSystemBars()
    }
}


