package com.example.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import com.example.presentation.R

@Composable
fun ColumnScope.AnimatedAction(
    modifier: Modifier,
    visible: Boolean,
    onCloseClicked: () -> Unit,
    onRenameClicked: () -> Unit,
    onDeleteClicked: () -> Unit
    ) {



    val actionItems: List<ActionBarItem> = listOf(
        ActionBarItem(
            imageVector = Icons.Default.Close,
            text = R.string.close_action,
            contentDescription = "close",
            onClick = { onCloseClicked() }
        ),
        ActionBarItem(
            imageVector = Icons.Default.Delete,
            text = R.string.delete_action,
            contentDescription = "delete",
            onClick = { onDeleteClicked() }
        ),
        ActionBarItem(
            imageVector = Icons.Default.Edit,
            text = R.string.rename_action,
            contentDescription = "rename",
            onClick = { onRenameClicked() }
        ),
    )

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
        ) + fadeIn(
            animationSpec = tween(durationMillis = 300)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
        ) + fadeOut(
            animationSpec = tween(durationMillis = 200)
        ),
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .wrapContentHeight()
            .padding(horizontal = 18.dp)
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = Color(0xE6C4C4C4),
                    shape = RoundedCornerShape(8.dp)
                )
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                18.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            actionItems.fastForEach { actionItem ->
                Column(
                    modifier = modifier.clickable {
                        actionItem.onClick()
                    },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = modifier.height(10.dp))
                    Icon(
                        imageVector = actionItem.imageVector,
                        contentDescription = actionItem.contentDescription,
                        tint = Color.White,
                        modifier = Modifier
                            .size(22.dp)
                    )

                    Text(
                        text = stringResource(actionItem.text),
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = modifier.height(5.dp))
                }
            }
        }
    }
}

data class ActionBarItem(
    val imageVector: ImageVector,
    val text: Int,
    val contentDescription: String,
    val onClick: () -> Unit
)

