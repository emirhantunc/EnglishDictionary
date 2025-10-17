package com.example.presentation.ui.quizzes.wordmemory

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.presentation.R
import com.example.presentation.ui.routes.QuizScreenRoutes
import com.example.presentation.viewmodel.QuizViewModel
import com.example.ui.ResourceStates
import com.example.ui.components.ProgressBar
import com.example.ui.toastMessage


@Composable
fun WordMemoryScreen(
    modifier: Modifier,
    viewModel: QuizViewModel,
    folderId: Int,
    context: Context,
    navHostController: NavHostController,
) {
    LaunchedEffect(Unit) {
        viewModel.getWords(folderId)
        viewModel.getFolderById(id = folderId)
    }

    val resourcesStates = viewModel.resourcesStates.collectAsStateWithLifecycle().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val state = resourcesStates) {
            is ResourceStates.Error -> {
                toastMessage(message = state.message, context = context)
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
                val score = remember {
                    mutableIntStateOf(0)
                }
                var next by remember { mutableStateOf(false) }
                var isAnswerVisible by remember { mutableStateOf(false) }
                folderState?.let { folder ->
                    Text(text = folder.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(modifier = modifier.height(5.dp))
                    Text(
                        text = stringResource(
                            id = R.string.your_score, score.intValue, wordsState.size
                        )
                    )
                }
                Spacer(modifier = modifier.height(25.dp))
                Text(
                    text = stringResource(id = R.string.word_memory_title),
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(85.dp)
                        .padding(
                            vertical = 6.dp, horizontal = 6.dp
                        ), colors = CardDefaults.cardColors(
                        containerColor = Color(0x80E7FDFE)
                    ), shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = currentWord.definition,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                WordMemoryAnimation(
                    modifier = modifier,
                    correctAnswer = currentWord.word,
                    isAnswerVisible = isAnswerVisible,
                    correctState = {
                        score.intValue++
                    })

                Spacer(modifier = modifier.height(15.dp))
                listOf<ButtonItem>(
                    ButtonItem(
                        R.string.word_memory_submit_button, onClick = {
                            isAnswerVisible = true
                            next = true
                        }, buttonColor = 0xFFFFD54F, visible = !next
                    ),
                    ButtonItem(
                        R.string.word_memory_skip_button, onClick = {
                            if (itemIterator.hasNext()) {
                                currentWord = itemIterator.next()
                            } else {
                                next = true
                            }
                        }, buttonColor = 0xFFFFFFFF, visible = !next
                    ),
                    ButtonItem(
                        R.string.word_memory_next_button, onClick = {
                            if (itemIterator.hasNext()) {
                                currentWord = itemIterator.next()
                                isAnswerVisible = false
                                next = false
                            } else {
                                navHostController.navigate(QuizScreenRoutes.QuizFinished.route)
                            }
                        }, buttonColor = 0xFFFFD54F, visible = next
                    ),
                ).forEach { item ->
                    if (item.visible) {
                        OutlinedButton(
                            onClick = {
                                item.onClick()
                            },
                            modifier = modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(item.buttonColor)
                            )
                        ) {
                            Text(text = stringResource(item.text))
                        }
                    }
                }
            }
        }
    }
}

data class ButtonItem(
    val text: Int, val onClick: () -> Unit, val buttonColor: Long, val visible: Boolean
)
