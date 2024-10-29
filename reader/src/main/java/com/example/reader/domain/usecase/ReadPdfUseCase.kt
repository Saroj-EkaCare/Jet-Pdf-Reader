package com.example.reader.domain.usecase

import android.net.Uri
import com.example.reader.data.model.PdfDocuments
import com.example.reader.domain.repository.PdfReadRepository

class ReadPdfUseCase(private val pdfReadRepository: PdfReadRepository) {
    suspend operator fun invoke(url: String): PdfDocuments {
        return pdfReadRepository.readPdfFromUrl(url)
    }

    suspend operator fun invoke(uri: Uri): PdfDocuments {
        return pdfReadRepository.readPdfFromUri(uri)
    }
}