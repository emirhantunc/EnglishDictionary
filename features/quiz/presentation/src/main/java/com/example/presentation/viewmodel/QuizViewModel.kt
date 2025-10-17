package com.example.presentation.viewmodel

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.GetQuizFoldersUseCase
import com.example.domain.usecases.QuizUseCases
import com.example.presentation.mapper.toFolderState
import com.example.presentation.mapper.toFolderStateList
import com.example.presentation.mapper.toWordStateList
import com.example.presentation.model.QuizFolderState
import com.example.presentation.model.QuizWordState
import com.example.ui.ResourceStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import androidx.core.net.toUri

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizUseCases: QuizUseCases
) : ViewModel() {

    private val _foldersState = MutableStateFlow<List<QuizFolderState>>(emptyList())
    private val _selectedFolderState = MutableStateFlow<QuizFolderState?>(null)
    private val _wordsState = MutableStateFlow<List<QuizWordState>>(emptyList())
    private val _resourceStates = MutableStateFlow<ResourceStates>(ResourceStates.Initial)
    val foldersState: StateFlow<List<QuizFolderState>> =
        _foldersState.onStart { getAllFolders() }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList<QuizFolderState>()
        )
    val selectedFolderState: StateFlow<QuizFolderState?> = _selectedFolderState
    val wordsStates: StateFlow<List<QuizWordState>> = _wordsState
    val resourcesStates: StateFlow<ResourceStates> = _resourceStates


    fun getAllFolders() {
        viewModelScope.launch {
            try {
                quizUseCases.getQuizFoldersUseCase().collect { folder ->
                    _foldersState.value = folder.toFolderStateList()
                }
            } catch (e: Exception) {
                Log.d("quiz exception: ", e.toString())
            }
        }
    }

    fun getFolderById(id: Int) {
        viewModelScope.launch {
            try {
                _selectedFolderState.value =
                    quizUseCases.getQuizFolderByIdUseCase(id).toFolderState()
            } catch (e: Exception) {
                Log.d("quiz exception: ", e.toString())
            }
        }
    }

    fun getWords(folderId: Int) {
        viewModelScope.launch {
            try {
                _resourceStates.value = ResourceStates.Loading
                _wordsState.value = quizUseCases.getQuizWordsUseCase(folderId).toWordStateList()
                _resourceStates.value = ResourceStates.Success
            } catch (e: Exception) {
                _resourceStates.value = ResourceStates.Error(e.message.toString())
                Log.d("quiz exception: ", e.toString())
            }
        }
    }

    fun listenAudio(context: Context, url: String) {
        val mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )

        try {
            mediaPlayer.setDataSource(context, url.toUri())
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener { mp ->
                mp.start()
            }
            Log.d("audioProcess", "Success $url")
        } catch (e: IOException) {
            Log.d("audioException", e.toString())
        }
    }
}