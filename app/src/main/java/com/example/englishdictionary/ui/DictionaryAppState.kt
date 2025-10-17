package com.example.englishdictionary.ui

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.englishdictionary.R
import com.example.presentation.ui.ExplorerScreen
import com.example.presentation.ui.QuizTopIconClick
import com.example.presentation.ui.graphbuilder.QuizGraph
import com.example.presentation.ui.SearchScreen
import com.example.presentation.ui.WordScreen
import com.example.presentation.viewmodel.ExplorerViewModel
import com.example.presentation.viewmodel.QuizViewModel
import com.example.presentation.viewmodel.SearchViewModel


sealed class Screen(val route: String) {
    object Explore : Screen("explorer")
    object Search : Screen("search")
    object Quiz : Screen("quiz_graph")
    object Word : Screen("word/{folderId}")
}


sealed class TopIconClicked() {
    object WordClicked : TopIconClicked()
}


enum class TopBarContent(
    @StringRes val title: Int,
    val route: String,
    var imageVector: ImageVector? = null,
    var onTopIconClicked: TopIconClicked = TopIconClicked.WordClicked,
) {
    EXPLORE(
        title = R.string.explore_name,
        route = Screen.Explore.route,
    ),
    WORD(
        title = R.string.explore_name,
        route = Screen.Word.route,
        imageVector = Icons.Default.ArrowBackIosNew,
        onTopIconClicked = TopIconClicked.WordClicked
    ),
    SEARCH(
        title = R.string.search_name,
        route = Screen.Search.route,
    ),
    QUIZ(
        title = R.string.quiz_name,
        route = Screen.Quiz.route,
    )
}

enum class MainGraph(
    val route: String
) {
    MAIN(route = "main_graph"),
}

enum class BottomItem(
    val route: String,
    @StringRes val title: Int,
    val drawerTabIcon: ImageVector,
) {
    EXPLORE(
        route = Screen.Explore.route,
        title = R.string.explore_name,
        drawerTabIcon = Icons.Default.Explore

    ),
    WORD(
        route = Screen.Word.route,
        title = R.string.explore_name,
        drawerTabIcon = Icons.Default.Explore
    ),
    SEARCH(
        route = Screen.Search.route,
        title = R.string.search_name,
        drawerTabIcon = Icons.Default.Search
    ),
    QUIZ(
        route = Screen.Quiz.route,
        title = R.string.quiz_name,
        drawerTabIcon = Icons.Default.Games
    )
}

@SuppressLint("ContextCastToActivity")
@Composable
@OptIn(ExperimentalAnimationApi::class)
fun Home(
    appState: DictionaryAppState,
    innerPadding: PaddingValues,
    modifier: Modifier
) {
    NavHost(
        navController = appState.controller,
        startDestination = MainGraph.MAIN.route,
        modifier = modifier
            .padding(innerPadding)
            .background(Color.White)
    ) {
        navigation(
            startDestination = Screen.Search.route,
            route = MainGraph.MAIN.route
        ) {
            composable(
                route = Screen.Search.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                },
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    appState.controller.getBackStackEntry(MainGraph.MAIN.route)
                }
                val searchViewModel = hiltViewModel<SearchViewModel>(parentEntry)
                SearchScreen(viewModel = searchViewModel, modifier = modifier)
            }
            composable(
                route = Screen.Quiz.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                },
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    appState.controller.getBackStackEntry(MainGraph.MAIN.route)
                }
                val quizViewModel = hiltViewModel<QuizViewModel>(parentEntry)
                QuizGraph(
                    imageVector = {
                        TopBarContent.QUIZ.imageVector = it
                    },
                    topIconClicked = { event ->
                    },
                    modifier = modifier,
                    viewModel = quizViewModel
                )
            }

            composable(
                route = Screen.Explore.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                },
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    appState.controller.getBackStackEntry(MainGraph.MAIN.route)
                }
                val exploreViewModel = hiltViewModel<ExplorerViewModel>(parentEntry)
                ExplorerScreen(
                    modifier = modifier,
                    viewModel = exploreViewModel,
                    navController = appState.controller
                )
            }
            composable(
                route = Screen.Word.route,
                arguments = listOf(
                    navArgument("folderId") { type = NavType.IntType }
                ),
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                },
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    appState.controller.getBackStackEntry(MainGraph.MAIN.route)
                }
                val folderId = backStackEntry.arguments?.getInt("folderId") ?: 0
                val exploreViewModel = hiltViewModel<ExplorerViewModel>(parentEntry)
                WordScreen(
                    modifier = modifier,
                    viewModel = exploreViewModel,
                    folderId = folderId
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberDictionaryAppState(
    controller: NavHostController = rememberNavController(),
    route: String? = controller.currentBackStackEntryAsState().value?.destination?.route
): DictionaryAppState = remember(controller, route) {
    DictionaryAppState(controller, route)
}

class DictionaryAppState(
    val controller: NavHostController,
    var route: String?
) {
    private val bottomItems = BottomItem.entries.toTypedArray()
    val bottomItemSelected: BottomItem
        @Composable get() {
            return bottomItems.firstOrNull { it.route == route } ?: BottomItem.SEARCH
        }
    private val topItems = TopBarContent.entries.toTypedArray()
    val currentRouteTopBar: TopBarContent
        @Composable get() {
            return topItems.firstOrNull { it.route == route }
                ?: TopBarContent.SEARCH
        }

    fun navigateToExplorer() {
        if (route != Screen.Explore.route) {
            controller.navigate(route = Screen.Explore.route)
        }
    }

    fun navigateSearch() {
        if (route != Screen.Search.route) {
            controller.navigate(route = Screen.Search.route)
        }
    }

    fun navigateQuiz() {
        if (route != Screen.Quiz.route) {
            controller.navigate(route = Screen.Quiz.route)
        }
    }
}