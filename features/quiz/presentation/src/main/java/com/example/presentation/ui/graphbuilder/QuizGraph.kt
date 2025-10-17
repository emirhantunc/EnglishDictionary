package com.example.presentation.ui.graphbuilder

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.presentation.ui.quizzes.flashcard.FlashCardScreen
import com.example.presentation.ui.QuizScreen
import com.example.presentation.ui.selections.SelectionScreen
import com.example.presentation.ui.quizzes.audio.AudioScreen
import com.example.presentation.ui.quizzes.quizfinished.QuizFinished
import com.example.presentation.ui.quizzes.wordmemory.WordMemoryScreen
import com.example.presentation.ui.routes.QuizScreenRoutes
import com.example.presentation.viewmodel.QuizViewModel
import com.example.ui.toastMessage

@SuppressLint("UnrememberedMutableState")
@Composable
fun NavGraphBuilder.QuizGraph(
    imageVector: (ImageVector?) -> Unit,
    modifier: Modifier,
    viewModel: QuizViewModel,
    onTopClicked: () -> Boolean?,
    onActionConsumed: () -> Unit
) {
    val localContext = LocalContext.current
    val navHostController: NavHostController = rememberNavController()
    var onTopClicked by mutableStateOf(onTopClicked())

    LaunchedEffect(onTopClicked) {
        if (onTopClicked == true) {
            try {
                if (navHostController.previousBackStackEntry != null) {
                    navHostController.popBackStack()
                }
            } catch (e: Exception) {
                toastMessage(e.message.toString(), localContext)
            } finally {
                onActionConsumed()
            }
        }
    }

    NavHost(
        navController = navHostController,
        startDestination = QuizScreenRoutes.Quiz.route,
        modifier = modifier
    ) {
        composable(
            route = QuizScreenRoutes.Quiz.route
        ) {
            imageVector(null)
            QuizScreen(
                modifier = modifier,
                viewModel = viewModel,
                navController = navHostController
            )
        }
        composable(
            route = QuizScreenRoutes.Selection.route,
            arguments = listOf(
                navArgument("folderId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val folderId = requireNotNull(backStackEntry.arguments?.getInt("folderId")) {
                "folderId argument is required but was not provided."
            }
            imageVector(Icons.AutoMirrored.Filled.ArrowBackIos)
            SelectionScreen(
                modifier = modifier,
                navController = navHostController,
                folderId = folderId
            )
        }
        composable(
            route = QuizScreenRoutes.WordMemory.route, arguments = listOf(
                navArgument("folderId") {
                    type = NavType.IntType
                }
            )) { backStackEntry ->
            val folderId = requireNotNull(backStackEntry.arguments?.getInt("folderId")) {
                "folderId argument is required but was not provided."
            }
            imageVector(Icons.AutoMirrored.Filled.ArrowBackIos)
            WordMemoryScreen(
                modifier = modifier,
                viewModel = viewModel,
                folderId = folderId,
                context = localContext,
                navHostController = navHostController
            )
        }
        composable(
            route = QuizScreenRoutes.FlashCard.route, arguments = listOf(
                navArgument("folderId") {
                    type = NavType.IntType
                }
            )) { backStackEntry ->
            val folderId = backStackEntry.arguments?.getInt("folderId") ?: 0
            imageVector(Icons.AutoMirrored.Filled.ArrowBackIos)
            FlashCardScreen(
                modifier = modifier,
                viewModel = viewModel,
                folderId = folderId,
                context = localContext,
                navHostController = navHostController,
            )
        }
        composable(
            route = QuizScreenRoutes.Audio.route, arguments = listOf(
                navArgument("folderId") {
                    type = NavType.IntType
                }
            )) { backStackEntry ->
            val folderId = backStackEntry.arguments?.getInt("folderId") ?: 0
            imageVector(Icons.AutoMirrored.Filled.ArrowBackIos)
            AudioScreen(
                modifier = modifier,
                viewModel = viewModel,
                folderId = folderId,
                context = localContext,
                navHostController = navHostController
            )
        }
        composable(route = QuizScreenRoutes.QuizFinished.route) {
            QuizFinished(modifier = modifier, navHostController = navHostController)
        }
    }
}