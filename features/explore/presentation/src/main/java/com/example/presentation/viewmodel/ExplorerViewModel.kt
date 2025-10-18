package com.example.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.UseCasesExplore
import com.example.presentation.mapper.toFolder
import com.example.presentation.mapper.toFolderStateList
import com.example.presentation.mapper.toWordStateList
import com.example.presentation.model.FolderState
import com.example.presentation.model.WordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExplorerViewModel @Inject constructor(
    private val useCases: UseCasesExplore
) : ViewModel() {

    init {
        getFolders()
    }

    private val _folderState = MutableStateFlow<List<FolderState>>(emptyList())
    private val _wordState = MutableStateFlow<List<WordState>>(emptyList())

    val folderState: StateFlow<List<FolderState>> = _folderState
    val wordState: StateFlow<List<WordState>> = _wordState


    fun insertFolders(folder: FolderState) {
        viewModelScope.launch {
            useCases.insertFoldersUseCase(folder.toFolder())
            getFolders()
        }
    }

    fun deleteFolders(folder: FolderState) {
        viewModelScope.launch {
            useCases.deleteFolderUseCase(folder = folder.toFolder())
        }
    }

    fun getFolders() {
        viewModelScope.launch {
            try {
                useCases.getFoldersUseCase().collect { folders ->
                    _folderState.value = folders.toFolderStateList()
                }

            } catch (e: Exception) {
                Log.d("exception", e.message.toString())
            }


        }
    }

    fun getWords(folderId: Int) {
        viewModelScope.launch {
            try {
                _wordState.value = useCases.getWordsUseCase(folderId = folderId).toWordStateList()
            } catch (e: Exception) {
                Log.d("getWords", e.message.toString())
            }
        }
    }
}