package com.example.presentation.ui.quizzes.quizfinished

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.presentation.R
import com.example.presentation.ui.routes.QuizScreenRoutes
import com.example.ui.components.ApplicationButton

@Composable
fun QuizFinished(modifier: Modifier, navHostController: NavHostController) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.test_finished),
            fontSize = 22.sp,
            color = Color.Black,
            fontWeight = FontWeight.Black
        )

        Spacer(modifier.height(8.dp))

        ApplicationButton(
            modifier = modifier,
            onClicked = {
                navHostController.navigate(QuizScreenRoutes.Quiz.route) {
                    popUpTo(navHostController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            },
            text = R.string.finish_button,
            color = 0xFFFFD54F
        )
    }
}