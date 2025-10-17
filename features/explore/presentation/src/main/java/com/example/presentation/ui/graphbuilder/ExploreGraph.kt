package com.example.presentation.ui.graphbuilder

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.example.presentation.ui.explore.ExplorerScreen
import com.example.presentation.ui.words.WordScreen
import com.example.presentation.ui.routes.ExploreScreenRoutes
import com.example.presentation.viewmodel.ExplorerViewModel
import com.example.ui.toastMessage


@SuppressLint("UnrememberedMutableState")
@Composable
fun NavGraphBuilder.ExploreGraph(
    imageVector: (ImageVector?) -> Unit,
    modifier: Modifier,
    viewModel: ExplorerViewModel,
    onTopClicked: () -> Boolean?,
    onActionConsumed: () -> Unit
) {
    val navHostController: NavHostController = rememberNavController()
    var onTopClicked by mutableStateOf(onTopClicked())
    val localContext = LocalContext.current

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
        startDestination = ExploreScreenRoutes.Explore.route,
        modifier = modifier
    ) {
        imageVector(null)
        composable(
            route = ExploreScreenRoutes.Explore.route
        ) {
            ExplorerScreen(
                modifier = modifier,
                navController = navHostController,
                viewModel = viewModel
            )
        }
        composable(
            route = ExploreScreenRoutes.Word.route,
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
            WordScreen(folderId = folderId, modifier = modifier, viewModel = viewModel)
        }
    }
}