package com.example.presentation.ui.quizzes.flashcard

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.presentation.R
import com.example.presentation.ui.routes.QuizScreenRoutes
import com.example.presentation.viewmodel.QuizViewModel
import com.example.ui.ResourceStates
import com.example.ui.components.ApplicationButton
import com.example.ui.components.ProgressBar
import com.example.ui.toastMessage

@Composable
fun NavGraphBuilder.FlashCardScreen(
    modifier: Modifier,
    viewModel: QuizViewModel,
    folderId: Int,
    context: Context,
    navHostController: NavHostController
) {
    LaunchedEffect(Unit) {
        viewModel.getFolderById(id = folderId)
        viewModel.getWords(folderId = folderId)
    }
    val resourceStates = viewModel.resourcesStates.collectAsStateWithLifecycle().value
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val state = resourceStates) {
            is ResourceStates.Error -> {
                toastMessage(message = state.message.toString(), context = context)
            }

            ResourceStates.Initial -> {

            }

            ResourceStates.Loading -> {
                ProgressBar(modifier = modifier)
            }

            ResourceStates.Success -> {
                val wordsState = viewModel.wordsStates.collectAsStateWithLifecycle().value
                val folderState = viewModel.selectedFolderState.collectAsStateWithLifecycle().value
                val itemIterator = remember(wordsState) { wordsState.drop(1).iterator() }
                var currentWord by remember(wordsState) { mutableStateOf(wordsState.first()) }
                var currentIndex by remember { mutableIntStateOf(0) }

                folderState?.let {
                    Text(
                        text = it.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                }
                Spacer(modifier = modifier.height(5.dp))

                Text(
                    text = "${currentIndex}/${wordsState.size}",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = modifier.height(25.dp))

                Text(
                    text = stringResource(R.string.flash_card_tap_card),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.Black
                )
                Spacer(modifier = modifier.height(10.dp))

                FlippableCard(modifier = modifier, front = {
                    Text(
                        text = currentWord.definition,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        modifier = modifier.padding(vertical = 7.dp, horizontal = 7.dp)
                    )

                }, back = {
                    Text(
                        text = currentWord.word,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Black,
                        modifier = modifier.padding(vertical = 7.dp, horizontal = 7.dp)
                    )

                })
                Spacer(modifier = modifier.height(10.dp))
                ApplicationButton(
                    modifier = modifier, onClicked = {
                        if (itemIterator.hasNext()) {
                            currentWord = itemIterator.next()
                            currentIndex++
                        } else {
                            navHostController.navigate(QuizScreenRoutes.QuizFinished.route)

                        }
                    }, text = R.string.word_memory_next_button,
                    color = 0xFFFFD54F
                )
            }
        }
    }
}