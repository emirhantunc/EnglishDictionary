package com.example.presentation.viewmodel

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.UseCasesDictionary
import com.example.presentation.mapper.toFolderRoomStateList
import com.example.presentation.mapper.toWordRoom
import com.example.presentation.mapper.toWordStateList
import com.example.presentation.model.network.WordState
import com.example.presentation.model.room.FolderRoomState
import com.example.presentation.model.room.WordRoomPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import com.example.utils.ResourceStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCasesDictionary
) : ViewModel() {
    private val _wordState = MutableStateFlow<List<WordState>>(emptyList())
    private val _folderList = MutableStateFlow<List<FolderRoomState>>(emptyList())
    private val _resourceStates = MutableStateFlow<ResourceStates>(ResourceStates.Initial)

    val wordState: StateFlow<List<WordState>> = _wordState
    val folderList: StateFlow<List<FolderRoomState>> = _folderList
    val resourceStates: StateFlow<ResourceStates> = _resourceStates


    init {
        getAllFolders()
    }
    fun getWordMeaning(word: String) {
        viewModelScope.launch {
            try {
                _resourceStates.value = ResourceStates.Loading
                _wordState.update {
                    useCases.getWordMeaningUseCase(word = word).toWordStateList()
                }
                _resourceStates.value = ResourceStates.Success
            } catch (e: Exception) {
                _resourceStates.value = ResourceStates.Error(e.toString())
                Log.d("dictionaryViewmodel", e.toString())
            }
        }
    }

    fun listenAudio(context: Context, url: String) {
        val mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )

        try {
            mediaPlayer.setDataSource(context, Uri.parse(url))
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener { mp ->
                mp.start()
            }
        } catch (e: IOException) {
            Log.d("audioException", e.toString())
        }
    }

    fun insertWord(word: String, folderId: Int, definition: String) {
        val wordRoomPresentation =
            WordRoomPresentation(id = 0, word = word, folderId = folderId, definition = definition)
        viewModelScope.launch {
            try {
                useCases.insertWordUseCase(wordRoomPresentation.toWordRoom())

            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }

    fun getAllFolders() {
        viewModelScope.launch {
            try {
                useCases.getAllFoldersUseCase().collect { folders ->
                    _folderList.value = folders.toFolderRoomStateList()
                }
            } catch (e: Exception) {
                throw Exception(e)
            }

        }
    }
}