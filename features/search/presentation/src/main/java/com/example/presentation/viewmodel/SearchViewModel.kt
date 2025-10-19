package com.example.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.UseCasesSearch
import com.example.presentation.mapper.toSearchFolderWithCountStateList
import com.example.presentation.mapper.toWordRoom
import com.example.presentation.mapper.toWordStateList
import com.example.presentation.model.network.WordState
import com.example.presentation.model.room.SearchFolderWithCountState
import com.example.presentation.model.room.SearchWordPresentation
import com.example.ui.ResourceStates
import com.example.utils.isInternetAvailable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCasesSearch
) : ViewModel() {
    private val _wordState = MutableStateFlow<List<WordState>>(emptyList())
    private val _folderList = MutableStateFlow<List<SearchFolderWithCountState>>(emptyList())
    private val _resourceStates = MutableStateFlow<ResourceStates>(ResourceStates.Initial)

    val wordState: StateFlow<List<WordState>> = _wordState.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000L),
        emptyList()
    )
    val folderList: StateFlow<List<SearchFolderWithCountState>> =
        _folderList.onStart { getAllFolders() }.stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000L),
            emptyList()
        )
    val resourceStates: StateFlow<ResourceStates> = _resourceStates

    fun getWordMeaning(word: String, context: Context) {
        viewModelScope.launch {
            if (context.isInternetAvailable()) {
                try {
                    _resourceStates.value = ResourceStates.Loading
                    _wordState.update {
                        useCases.getWordMeaningUseCase(word = word).toWordStateList()
                    }
                    _resourceStates.value = ResourceStates.Success
                } catch (e: Exception) {
                    _resourceStates.value = ResourceStates.Error(e.toString())
                    e.printStackTrace()
                }
            } else {
                _resourceStates.value = ResourceStates.Error("Check Your Internet Connection")
            }
        }
    }

    fun insertWord(word: String, folderId: Int, definition: String, audioUrl: String) {
        val searchWordPresentation =
            SearchWordPresentation(
                id = 0,
                word = word,
                folderId = folderId,
                definition = definition,
                audioUrl = audioUrl
            )
        viewModelScope.launch {
            try {
                useCases.insertWordUseCase(searchWordPresentation.toWordRoom())

            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }

    fun getAllFolders() {
        viewModelScope.launch {
            try {
                useCases.getAllFoldersUseCase().collect { folders ->
                    _folderList.value = folders.toSearchFolderWithCountStateList()
                }
            } catch (e: Exception) {
                throw Exception(e)
            }

        }
    }
}