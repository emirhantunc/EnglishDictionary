package com.example.presentation.ui.response

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import com.example.presentation.R
import com.example.presentation.model.network.DefinitionState
import com.example.presentation.model.room.WordRoomPresentation

@Composable
fun Definitions(
    modifier: Modifier = Modifier,
    definitions: List<DefinitionState>,
    bottomSheet: (WordRoomPresentation) -> Unit
) {

    definitions.fastForEach { definition ->
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
                text = definition.definition,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                ),
                modifier = modifier.padding(8.dp)
            )
            Text(
                text = definition.example,
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
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd,
            ) {
                Button(
                    onClick = {
                        bottomSheet(
                            WordRoomPresentation(
                                id = 0,
                                word = "word",
                                folderId = 0,
                                definition = definition.definition,
                                audioUrl = ""
                            )
                        )

                    },
                    modifier = modifier
                        .height(40.dp)
                        .width(145.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFD54F)
                    ),
                    contentPadding = PaddingValues(end = 12.dp)
                ) {
                    Text(
                        stringResource(id = R.string.add_word_list),
                        fontSize = 12.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = modifier.width(5.dp))
                    Icon(
                        modifier = modifier.size(16.dp),
                        imageVector = Icons.Default.AddCircleOutline,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(12.dp))
    }
}