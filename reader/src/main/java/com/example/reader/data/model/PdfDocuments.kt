package com.example.reader.data.model

data class PdfDocuments(
    val pageCount: Int,
    val getPage: suspend (Int) -> PdfPage,
    val close: () -> Unit
)