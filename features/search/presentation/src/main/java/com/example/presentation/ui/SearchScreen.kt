package com.example.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.presentation.viewmodel.SearchViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.example.presentation.model.room.WordRoomPresentation
import com.example.presentation.ui.components.SearchTextField
import com.example.presentation.ui.response.ResponseScreen

@Composable
fun NavGraphBuilder.SearchScreen(
    modifier: Modifier,
    viewModel: SearchViewModel,
) {
    val context = LocalContext.current
    val wordState = viewModel.wordState.collectAsStateWithLifecycle().value
    val resourceStates = viewModel.resourceStates.collectAsStateWithLifecycle().value
    var showBottomSheet by remember { mutableStateOf(false) }
    var wordRoomPresentation by remember { mutableStateOf<WordRoomPresentation?>(null) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showBottomSheet) {
            val folders = viewModel.folderList.collectAsStateWithLifecycle().value

            wordRoomPresentation?.let { it ->
                SaveBottomSheet(modifier = modifier, folders = folders, onDismiss = {
                    showBottomSheet = false
                }, folderSelected = { id ->
                    viewModel.insertWord(it.word, id, it.definition,it.audioUrl)
                })
            }
        }

        SearchTextField(modifier = modifier, search = { word ->
            viewModel.getWordMeaning(word)

        }, wordList = wordState)
        Spacer(modifier = modifier.height(15.dp))
        ResponseScreen(
            modifier = modifier,
            context = context,
            resourceStates = resourceStates,
            wordState = wordState,
            bottomSheet = { item ->
                wordRoomPresentation = item
                showBottomSheet = true
            })
    }
}