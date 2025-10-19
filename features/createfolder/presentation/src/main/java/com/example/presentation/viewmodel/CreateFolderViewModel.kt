package com.example.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.CreateFolderUseCase
import com.example.presentation.mapper.toCreateFolder
import com.example.presentation.model.CreateFolderPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreateFolderViewModel @Inject constructor(
    private val createFolderUseCase: CreateFolderUseCase
) : ViewModel() {
    var name by mutableStateOf("")
        private set

    fun onNameChange(newName: String) {
        name = newName
    }

    fun createFolder(list: CreateFolderPresentation) {
        viewModelScope.launch {
            try {
                createFolderUseCase(folder = list.toCreateFolder())
            } catch (e: Exception) {
                Log.d("createFolderExc ", e.message.toString())
            }
        }
    }
}