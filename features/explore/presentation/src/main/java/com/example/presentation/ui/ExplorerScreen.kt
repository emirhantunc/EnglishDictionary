package com.example.presentation.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.helper.ScreenManagement
import com.example.presentation.helper.ScreenManagement.*
import com.example.presentation.viewmodel.ExplorerViewModel

@Composable
fun ExplorerScreen(modifier: Modifier, viewModel: ExplorerViewModel = hiltViewModel()) {
    var currentScreen by remember { mutableStateOf<ScreenManagement>(Folder) }
    val folders = viewModel.folderState.collectAsStateWithLifecycle().value
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(14.dp)
    ) {
        when (val screen = currentScreen) {
            is Folder -> {

                FolderScreen(
                    onFolderClicked = { folderId ->
                        currentScreen = Word(folderId)
                    },
                    folders = folders,
                    createFolder = { folder ->
                        viewModel.insertFolders(folder = folder)
                    },
                    deleteFolder = { folder ->
                        viewModel.deleteFolders(folder = folder)
                    }
                )
            }

            is Word -> {
                WordScreen(viewModel = viewModel, folderId = screen.folderId)
            }
        }
        BackHandler {
            currentScreen = Folder
        }
    }
}