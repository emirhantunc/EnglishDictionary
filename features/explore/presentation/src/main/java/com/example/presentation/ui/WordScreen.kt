package com.example.presentation.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.viewmodel.ExplorerViewModel

@Composable
fun ColumnScope.WordScreen(
    modifier: Modifier = Modifier,
    folderId: Int,
    viewModel: ExplorerViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.getWords(folderId = folderId)
    }

    val words = viewModel.wordState.collectAsStateWithLifecycle().value

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(words) { word ->
            Card(
                modifier = modifier
                    .height(175.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0x80E7FDFE)
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = word.word,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = modifier.padding(8.dp)
                )
                Text(
                    text = word.definition,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic
                    ),
                    modifier = modifier.padding(
                        start = 16.dp,
                        bottom = 8.dp,
                        top = 4.dp
                    )
                )
                Spacer(modifier = modifier.height(12.dp))
            }
        }
    }
}