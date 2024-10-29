package com.example.reader.presentation.states

import com.example.reader.data.model.PdfDocuments

sealed class PdfReadState {
    object Idle : PdfReadState()
    object Loading : PdfReadState()
    data class Success(val pdfDocuments: PdfDocuments) : PdfReadState()
    data class Error(val message: String) : PdfReadState()
}