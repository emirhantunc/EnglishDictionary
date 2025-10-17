package com.example.presentation.ui.explore


import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.example.presentation.R
import com.example.presentation.model.FolderState
import com.example.presentation.ui.components.AnimatedIconAndAction
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
    var longClickedFolder by remember { mutableStateOf<FolderState?>(null) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(14.dp)
    ) {
        Text(
            text = stringResource(id = R.string.my_folders),
            fontSize = 25.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = modifier.height(5.dp))


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f)
                .background(Color.White)
                .padding(start = 7.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(folders) { folder ->
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .combinedClickable(onClick = {
                            navController.navigate(ExploreScreenRoutes.Word.createRoute(folderId = folder.id))
                        }, onLongClick = {
                            longClickedFolder = folder
                            showActionBar = true
                        }), colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ), elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )) {
                    Column(modifier = modifier.padding(8.dp)) {
                        Text(
                            text = folder.name,
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }


        if (showBottomSheet) {
            ExploreBottomSheet(
                modifier = modifier, onDismiss = {
                showBottomSheet = false
            }, createFolder = {
                viewModel.insertFolders(it)
            }, id = longClickedFolder?.id ?: 0
            )
        }

        AnimatedIconAndAction(modifier = modifier, visible = showActionBar, onCloseClicked = {
            showActionBar = false
        }, onDeleteClicked = {
            showActionBar = false
            longClickedFolder?.let { folder ->
                viewModel.deleteFolders(folder)
            }

        }, onRenameClicked = {
            showBottomSheet = true
            showActionBar = false

        }, showBottomSheet = {
            showBottomSheet = true
        })
    }
}