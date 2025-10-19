package com.example.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.presentation.R
import com.example.presentation.model.CreateFolderPresentation
import com.example.presentation.viewmodel.CreateFolderViewModel
import com.example.ui.components.ApplicationButton

@Composable
fun CreateFolderScreen(
    modifier: Modifier,
    viewModel: CreateFolderViewModel = hiltViewModel<CreateFolderViewModel>()
) {
    val name = viewModel.name

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 14.dp, vertical = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = {
                viewModel.onNameChange(it)
            },
            modifier = modifier
                .height(56.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(
                fontSize = 13.sp, color = Color.Black, fontWeight = FontWeight.Normal
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.Name_your_folder),
                    fontSize = 13.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            },
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.LightGray,
                unfocusedIndicatorColor = Color.LightGray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                cursorColor = Color.Black
            ),
        )
        Spacer(modifier = modifier.height(8.dp))
        ApplicationButton(
            modifier = modifier,
            onClicked = {
                viewModel.createFolder(CreateFolderPresentation(id = 0, name = name))
                viewModel.onNameChange("")
            },
            text = R.string.btn_txt,
            color = 0x00000000
        )
    }
}