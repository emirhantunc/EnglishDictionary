package com.example.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun ApplicationButton(modifier: Modifier, onClicked: () -> Unit, text: Int, color: Long) {
    OutlinedButton(
        onClick = {
            onClicked()
        }, colors = ButtonDefaults.buttonColors(
            containerColor = Color(color)
        ),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            stringResource(id = text), color = Color.Black
        )
    }
}