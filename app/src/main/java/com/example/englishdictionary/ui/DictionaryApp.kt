package com.example.englishdictionary.ui

import android.app.Activity
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
            TopBarApplication(currentRoute = dictionaryAppState.currentRouteTopBar)
        },
        bottomBar = {
            BottomBarApplication(
                selectedItem = dictionaryAppState.bottomItemSelected,
                onClickExplorer = { dictionaryAppState.navigateToExplorer() },
                onClickDictionary = { dictionaryAppState.navigateDictionary() },
                modifier = modifier
            )
        }) { innerPadding ->
        Home(innerPadding = innerPadding, appState = dictionaryAppState, modifier = modifier)
    }
}

@Composable
fun BottomBarApplication(
    selectedItem: BottomItem,
    onClickExplorer: () -> Unit,
    onClickDictionary: () -> Unit,
    modifier: Modifier
) {
    Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))

    NavigationBar(
        modifier = modifier
            .height(90.dp)
            .fillMaxWidth(),
        containerColor = Color.White
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = BottomItem.DICTIONARY.drawerTabIcon,
                    modifier = Modifier.size(20.dp),
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(id = BottomItem.DICTIONARY.title),
                    fontSize = 13.sp,
                    color = Color.Black
                )
            },
            selected = BottomItem.DICTIONARY == selectedItem,
            onClick = onClickDictionary,
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = BottomItem.EXPLORE.drawerTabIcon,
                    modifier = Modifier.size(20.dp),
                    contentDescription = null,
                )
            },
            label = {
                Text(
                    text = stringResource(id = BottomItem.EXPLORE.title),
                    fontSize = 13.sp,
                    color = Color.Black
                )
            },
            selected = BottomItem.EXPLORE == selectedItem,
            onClick = onClickExplorer,
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarApplication(
    currentRoute: TopBarContent
) {
    Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFFFD54F)
        ),
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
