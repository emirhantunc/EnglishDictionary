package com.example.presentation.ui.response

import android.content.Context
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.model.network.WordState
import com.example.presentation.model.room.WordRoomPresentation
import com.example.ui.ResourceStates
import com.example.ui.components.ProgressBar
import com.example.ui.toastMessage

@Composable
fun ResponseScreen(
    modifier: Modifier,
    context: Context,
    resourceStates: ResourceStates,
    wordState: List<WordState>,
    bottomSheet: (WordRoomPresentation) -> Unit
) {
    val flingBehavior = remember {
        object : FlingBehavior {
            override suspend fun ScrollScope.performFling(initialVelocity: Float): Float {
                val adjustedVelocity = initialVelocity * 0.5f
                return scrollBy(adjustedVelocity / 10)
            }
        }
    }
    when (resourceStates) {
        is ResourceStates.Error -> {
            val message = resourceStates.message
            toastMessage(message, context)
        }

        ResourceStates.Initial -> {}

        ResourceStates.Loading -> {
            Box(
                modifier = modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ProgressBar(modifier = modifier)
            }
        }

        ResourceStates.Success -> {
            val meanings = wordState.first().meanings
            LazyColumn(flingBehavior = flingBehavior) {
                items(meanings) { meaning ->
                    Text(
                        text = wordState.first().word,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = modifier.height(3.dp))
                    Text(
                        text = meaning.partOfSpeech,
                        style = TextStyle(
                            color = Color.DarkGray,
                            fontSize = 8.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic
                        )
                    )
                    Spacer(modifier = modifier.height(8.dp))
                    Definitions(
                        definitions = meaning.definitions,
                        bottomSheet = { it ->
                            bottomSheet(
                                WordRoomPresentation(
                                    id = 0,
                                    word = wordState.first().word,
                                    folderId = 0,
                                    definition = it.definition,
                                    audioUrl = wordState.first().phonetics.first().audio
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}