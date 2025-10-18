package com.example.presentation.ui.selections

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.presentation.R
import com.example.presentation.ui.routes.QuizScreenRoutes

@Composable
fun NavGraphBuilder.SelectionScreen(
    modifier: Modifier,
    folderId: Int,
    navController: NavHostController
) {
    val list: List<Selections> = listOf(
        Selections(
            selection = R.string.word_memory,
            definition = R.string.word_definition,
            imageVector = Icons.Outlined.Lightbulb,
            route = QuizScreenRoutes.WordMemory.createRoute(folderId = folderId)
        ),
        Selections(
            selection = R.string.flash_card,
            definition = R.string.flash_definition,
            imageVector = Icons.Outlined.Book,
            route = QuizScreenRoutes.FlashCard.createRoute(folderId = folderId)
        ),
        Selections(
            selection = R.string.audio,
            definition = R.string.audio_definition,
            imageVector = Icons.AutoMirrored.Outlined.VolumeUp,
            route = QuizScreenRoutes.Audio.createRoute(folderId = folderId)
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 14.dp, end = 14.dp)
    ) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(list) { item ->
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(item.route)
                        },
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Card(
                        modifier = modifier.size(145.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFC107)
                        ),
                        shape = RoundedCornerShape(6.dp)

                    ) {
                        Column(
                            modifier = modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(item.selection),
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily.Default,
                                fontStyle = FontStyle.Normal
                            )
                            Icon(
                                imageVector = item.imageVector,
                                contentDescription = null,
                                modifier.size(70.dp),
                                tint = Color.White
                            )
                        }
                    }
                    Text(
                        text = stringResource(item.definition),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Default,
                    )
                }
                Spacer(modifier = modifier.height(10.dp))
            }
        }
    }
}

