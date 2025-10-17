package com.example.presentation.ui.graphbuilder

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.runtime.Composable
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
import com.example.presentation.ui.QuizTopIconClick
import com.example.presentation.ui.selections.SelectionScreen
import com.example.presentation.ui.quizzes.audio.AudioScreen
import com.example.presentation.ui.quizzes.quizfinished.QuizFinished
import com.example.presentation.ui.quizzes.wordmemory.WordMemoryScreen
import com.example.presentation.ui.routes.QuizScreenRoutes
import com.example.presentation.viewmodel.QuizViewModel

@Composable
fun NavGraphBuilder.QuizGraph(
    imageVector: (ImageVector) -> Unit,
    modifier: Modifier,
    viewModel: QuizViewModel,
    topIconClicked: (QuizTopIconClick) -> Unit
) {
    val localContext = LocalContext.current
    val navHostController: NavHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = QuizScreenRoutes.Quiz.route,
        modifier = modifier
    ) {
        composable(
            route = QuizScreenRoutes.Quiz.route
        ) {
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
            topIconClicked(QuizTopIconClick.WordMemoryClicked)
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