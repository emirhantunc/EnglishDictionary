package com.example.presentation.ui.quizzes.wordmemory

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.helper.isEquals
import com.example.presentation.helper.modified

@Composable
fun ColumnScope.WordMemoryAnimation(
    modifier: Modifier,
    correctAnswer: String,
    isAnswerVisible: Boolean = false,
    correctState: () -> Unit
) {
    var userAnswer by remember { mutableStateOf("") }


    LaunchedEffect(isAnswerVisible) {
        if (isAnswerVisible) {
            if (isEquals(correctAnswer, userAnswer)) {
                correctState()
            }
        } else {
            userAnswer = ""
        }
    }

    AnimatedContent(
        targetState = isAnswerVisible,
        label = "AnswerTextFieldAnimation",
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(durationMillis = 400)
            ) togetherWith slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = 400)
            )
        }
    ) { isVisible ->
        if (!isVisible) {
            TextField(
                value = userAnswer, onValueChange = {
                    userAnswer = it
                },
                modifier = modifier.fillMaxWidth(), placeholder = {
                    Text(
                        text = stringResource(R.string.word_memory_place_holder),
                        fontSize = 13.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                },
                textStyle = TextStyle(
                    fontSize = 13.sp, color = Color.Black, fontWeight = FontWeight.Normal
                ), colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )
        } else {
            if (isEquals(userAnswer, correctAnswer)) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 21.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.word_memory_your_answer_correct),
                        style = TextStyle.modified()
                    )
                    Text(text = correctAnswer, style = TextStyle.modified())
                }
            } else {
                Column(
                    modifier = modifier
                        .wrapContentHeight()
                        .padding(horizontal = 21.dp),
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = stringResource(R.string.word_memory_your_answer),
                            style = TextStyle.modified(),
                        )
                        Spacer(modifier = modifier.weight(1f))
                        Text(
                            text = if (userAnswer.isEmpty()) "\"\"" else userAnswer,
                            style = TextStyle.modified()
                        )
                        Spacer(modifier = modifier.width(8.dp))

                    }
                    Row(modifier = modifier.fillMaxWidth()) {
                        Text(
                            text = stringResource(R.string.word_memory_correct_answer),
                            style = TextStyle.modified(),
                        )
                        Spacer(modifier = modifier.weight(1f))
                        Text(text = correctAnswer, style = TextStyle.modified())
                        Spacer(modifier = modifier.width(8.dp))

                    }
                }
            }
        }
    }
}



