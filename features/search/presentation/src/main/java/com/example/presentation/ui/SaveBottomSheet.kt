package com.example.presentation.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.model.room.SearchFolderState
import com.example.presentation.model.room.SearchFolderWithCountState
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ColumnScope.SaveBottomSheet(
    onDismiss: () -> Unit,
    folders: List<SearchFolderWithCountState>,
    folderSelected: (Int) -> Unit,
    modifier: Modifier
) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()


    ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = {
            onDismiss()
        }, sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.add_word_list),
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                fontStyle = FontStyle.Normal,
                textAlign = TextAlign.Start
            )
            LazyRow(
                modifier = modifier.weight(1f, fill = false),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(folders) { folder ->
                    Card(
                        modifier = modifier
                            .height(110.dp)
                            .width(130.dp)
                            .clickable {
                                folderSelected(folder.folder.id)
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        onDismiss()
                                    }
                                }
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        )
                    ) {
                        Text(
                            text = folder.folder.name,
                            fontSize = 12.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = modifier.padding(6.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onDismiss()
                        }
                    }
                },
                modifier = modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(4.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = stringResource(R.string.close),
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}


