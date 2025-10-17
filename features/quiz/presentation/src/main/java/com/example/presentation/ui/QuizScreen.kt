package com.example.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.presentation.R
import com.example.presentation.ui.routes.QuizScreenRoutes
import com.example.presentation.viewmodel.QuizViewModel

@Composable
fun NavGraphBuilder.QuizScreen(
    modifier: Modifier,
    viewModel: QuizViewModel,
    navController: NavController
) {
    val folders = viewModel.foldersState.collectAsStateWithLifecycle().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(14.dp)
    ) {
        Text(
            text = stringResource(id = R.string.my_folder),
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Default
        )
        Spacer(modifier.height(10.dp))
        LazyRow(
            modifier = modifier
                .fillMaxWidth()
        ) {
            items(folders) { folder ->
                Card(
                    modifier = modifier
                        .size(100.dp)
                        .clickable {
                            navController.navigate(
                                QuizScreenRoutes.Selection.createRoute(
                                    folderId = folder.id
                                )
                            )
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    ),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = folder.name,
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Default
                    )
                }
                Spacer(modifier = modifier.width(5.dp))
            }
        }
    }
}
