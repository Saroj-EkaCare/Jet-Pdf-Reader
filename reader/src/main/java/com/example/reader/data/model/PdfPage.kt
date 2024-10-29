package com.example.reader.data.model

import android.graphics.Bitmap

data class PdfPage(
    val width: Int,
    val height: Int,
    val render: suspend (Float) -> Bitmap
)