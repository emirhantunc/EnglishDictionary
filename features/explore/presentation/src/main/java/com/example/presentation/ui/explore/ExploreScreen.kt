package com.example.presentation.ui.explore


import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.presentation.R
import com.example.presentation.model.FolderState
import com.example.presentation.model.WordState
import com.example.presentation.ui.components.AnimatedAction
import com.example.presentation.ui.components.ExploreBottomSheet
import com.example.presentation.ui.routes.ExploreScreenRoutes
import com.example.presentation.viewmodel.ExplorerViewModel

@Composable
fun NavGraphBuilder.ExplorerScreen(
    modifier: Modifier, navController: NavController, viewModel: ExplorerViewModel
) {
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    var showActionBar by remember { mutableStateOf(false) }
    val folders = viewModel.folderState.collectAsStateWithLifecycle().value


    LaunchedEffect(folders) {
        val foldersWithWords = folders.filter { it.wordCount > 0 }
        if (foldersWithWords.isNotEmpty()) {
            viewModel.getWords(foldersWithWords.random().folderState.id)
        }
    }

    var longClickedFolder by remember { mutableStateOf<FolderState?>(null) }
    val collectionList =
        listOf<String>("Animals", "Traveling", "Sport") // just for ui design. Not a real list

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(14.dp)
    ) {
        Text(
            text = stringResource(id = R.string.my_folders),
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Black,
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = modifier.height(5.dp))

        LazyRow(
            modifier = Modifier
                .background(Color.White)
                .padding(start = 7.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(folders) { folder ->
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .size(height = 85.dp, width = 120.dp)
                        .combinedClickable(onClick = {
                            navController.navigate(ExploreScreenRoutes.Word.createRoute(folderId = folder.folderState.id))
                        }, onLongClick = {
                            longClickedFolder = folder.folderState
                            showActionBar = true
                        }), colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ), elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    )
                ) {
                    Column(modifier = modifier.padding(8.dp)) {
                        Text(
                            text = folder.folderState.name,
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = modifier.height(10.dp))

        Text(
            text = stringResource(id = R.string.quiz_collection),
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Black,
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = modifier.height(3.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp)

        ) {
            items(collectionList) {
                Card(
                    modifier = modifier.size(height = 85.dp, width = 120.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFD54F)
                    )
                ) {
                    Text(
                        text = it,
                        fontSize = 15.sp,
                        color = Color.White,
                        modifier = modifier.padding(horizontal = 6.dp, vertical = 6.dp)
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(5.dp))

        val targetHeight = if (showActionBar) 100.dp else 175.dp
        val animatedHeight by animateDpAsState(
            targetValue = targetHeight,
            animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
        )
        if (folders.isNotEmpty()) {
            val word = viewModel.wordState.collectAsStateWithLifecycle().value
            if (word.isNotEmpty()) {
                Card(
                    modifier = modifier
                        .height(animatedHeight)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0x80E7FDFE)
                    ),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = word.first().word,
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.Default,
                        fontStyle = FontStyle.Normal,
                        textAlign = TextAlign.Start,
                        modifier = modifier.padding(
                            horizontal = 12.dp, vertical = 8.dp
                        )
                    )
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(word) {
                            Text(
                                text = it.definition, style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Start,
                                    fontWeight = FontWeight.Normal,
                                    fontStyle = FontStyle.Italic
                                ), modifier = modifier.padding(
                                    horizontal = 16.dp, vertical = 8.dp
                                )
                            )
                        }

                    }
                    Spacer(modifier = modifier.height(5.dp))
                }
            }
        }

        Spacer(modifier = modifier.weight(1f))

        AnimatedAction(modifier = modifier, visible = showActionBar, onCloseClicked = {
            showActionBar = false
        }, onDeleteClicked = {
            showActionBar = false
            longClickedFolder?.let { folder ->
                viewModel.deleteFolders(folder)
            }

        }, onRenameClicked = {
            showBottomSheet = true
            showActionBar = false

        })

        if (showBottomSheet) {
            ExploreBottomSheet(
                modifier = modifier, onDismiss = {
                    showBottomSheet = false
                }, createFolder = {
                    viewModel.insertFolders(it)
                }, id = longClickedFolder?.id ?: 0,
                folderName = longClickedFolder?.name ?: ""
            )
        }
    }
}