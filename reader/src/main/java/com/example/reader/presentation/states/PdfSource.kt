package com.example.reader.presentation.states

sealed class PdfSource {
    data class Url(val url: String) : PdfSource()
    data class Uri(val uri: android.net.Uri) : PdfSource()
}