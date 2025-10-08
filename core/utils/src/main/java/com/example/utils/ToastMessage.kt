package com.example.utils

import android.content.Context
import android.widget.Toast

fun toastMessage(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}