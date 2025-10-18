package com.example.englishdictionary.ui

import android.app.Activity
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DictionaryApp() {
    val modifier: Modifier = Modifier
    val dictionaryAppState = rememberDictionaryAppState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {

            TopBarApplication(
                currentRoute = dictionaryAppState.currentRouteTopBar,
                modifier = modifier,
            )
        },
        bottomBar = {
            BottomBarApplication(
                selectedItem = dictionaryAppState.bottomItemSelected,
                dictionaryAppState = dictionaryAppState,
                modifier = modifier
            )
        }) { innerPadding ->
        Home(innerPadding = innerPadding, appState = dictionaryAppState, modifier = modifier)
    }
}

@Composable
fun BottomBarApplication(
    selectedItem: BottomItem,
    dictionaryAppState: DictionaryAppState,
    modifier: Modifier
) {
    val list: List<BottomItems> = listOf(
        BottomItems(
            icon = BottomItem.EXPLORE.drawerTabIcon,
            name = BottomItem.EXPLORE.title,
            onClick = { dictionaryAppState.navigateToExplorer() },
            bottomItem = BottomItem.EXPLORE
        ),
        BottomItems(
            icon = BottomItem.SEARCH.drawerTabIcon,
            name = BottomItem.SEARCH.title,
            onClick = { dictionaryAppState.navigateSearch() },
            bottomItem = BottomItem.SEARCH
        ),
        BottomItems(
            icon = BottomItem.CREATE_FOLDER.drawerTabIcon,
            name = BottomItem.CREATE_FOLDER.title,
            onClick = { dictionaryAppState.navigateCreateFolder() },
            bottomItem = BottomItem.CREATE_FOLDER
        ),
        BottomItems(
            icon = BottomItem.QUIZ.drawerTabIcon,
            name = BottomItem.QUIZ.title,
            onClick = { dictionaryAppState.navigateQuiz() },
            bottomItem = BottomItem.QUIZ
        )
    )
    Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))

    NavigationBar(
        modifier = modifier
            .height(90.dp)
            .fillMaxWidth(),
        containerColor = Color.White
    ) {
        list.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        modifier = Modifier.size(20.dp),
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.name),
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                },
                selected = item.bottomItem == selectedItem,
                onClick = item.onClick,
                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
                    .weight(1f),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarApplication(
    currentRoute: TopBarContent,
    modifier: Modifier,
) {
    Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFFFD54F)
        ),
        navigationIcon = {
            IconButton(
                onClick = {
                    TopBarContent.setActive(currentRoute.route)
                }, modifier = modifier
                    .size(35.dp)
                    .padding(start = 12.dp)
            ) {
                Icon(
                    imageVector = currentRoute.imageVector ?: return@IconButton,
                    contentDescription = null,
                    modifier = modifier.size(35.dp),
                    tint = Color.White
                )
            }
        },
        title = {
            Text(
                text = stringResource(id = currentRoute.title),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.Default,
                color = Color.Black
            )
        },
    )
}

fun Activity.hideSystemBars() {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    val controller = WindowInsetsControllerCompat(window, window.decorView)
    controller.hide(WindowInsetsCompat.Type.navigationBars())
    controller.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
}

