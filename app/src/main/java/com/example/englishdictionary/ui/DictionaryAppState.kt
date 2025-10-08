package com.example.englishdictionary.ui

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.englishdictionary.R
import com.example.presentation.ui.ExplorerScreen
import com.example.presentation.ui.SearchScreen
import com.example.presentation.viewmodel.SearchViewModel


sealed class Screen(val route: String) {
    object Explore : Screen("explore")
    object Dictionary : Screen("dictionary")
}

enum class TopBarContent(
    @StringRes val title: Int,
    val route: String,
) {
    EXPLORE(
        title = R.string.explore_name,
        route = Screen.Explore.route

    ),
    DICTIONARY(
        title = R.string.search_name,
        route = Screen.Dictionary.route
    )
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
    DICTIONARY(
        route = Screen.Dictionary.route,
        title = R.string.search_name,
        drawerTabIcon = Icons.Default.Search
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

    val searchViewModel = hiltViewModel<SearchViewModel>(
        viewModelStoreOwner = LocalContext.current as ComponentActivity
    )

    NavHost(
        navController = appState.controller,
        startDestination = Screen.Dictionary.route,
        modifier = modifier
            .padding(innerPadding)
            .background(Color.White)
    ) {

        composable(
            route = Screen.Dictionary.route,
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
        ) {
            SearchScreen(viewModel = searchViewModel, modifier = modifier)
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
        ) {
            ExplorerScreen(modifier = modifier)
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
            return bottomItems.firstOrNull { it.route == route } ?: BottomItem.DICTIONARY
        }
    private val topItems = TopBarContent.entries.toTypedArray()
    val currentRouteTopBar: TopBarContent
        @Composable get() {
            return topItems.firstOrNull { it.route == route }
                ?: TopBarContent.DICTIONARY
        }


    fun navigateToExplorer() {
        if (route != Screen.Explore.route) {
            controller.navigate(route = Screen.Explore.route)

        }
    }

    fun navigateDictionary() {
        if (route != Screen.Dictionary.route) {
            controller.navigate(route = Screen.Dictionary.route)
        }
    }
}
