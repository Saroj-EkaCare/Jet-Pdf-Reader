package com.example.reader.domain.repository

import android.net.Uri
import com.example.reader.data.model.PdfDocuments

interface PdfReadRepository {
    suspend fun readPdfFromUrl(url: String): PdfDocuments
    suspend fun readPdfFromUri(uri: Uri): PdfDocuments
}