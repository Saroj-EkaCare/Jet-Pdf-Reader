package com.example.reader.presentation.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import com.example.reader.data.model.PdfDocuments
import com.example.reader.data.model.PdfPage

@Composable
fun PdfPageView(
    pdfDocuments: PdfDocuments,
    pageIndex: Int,
    modifier: Modifier = Modifier
) {
    val pageState by produceState<PdfPage?>(initialValue = null, pdfDocuments, pageIndex) {
        value = pdfDocuments.getPage(pageIndex)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(
                pageState?.let { page ->
                    page.width.toFloat() / page.height.toFloat()
                } ?: 1f
            )
            .clipToBounds()
    ) {
        pageState?.let { page ->
            val bitmap by produceState<Bitmap?>(initialValue = null, page) {
                value = page.render(1f)
            }

            bitmap?.let { renderedBitmap ->
                Image(
                    bitmap = renderedBitmap.asImageBitmap(),
                    contentDescription = "PDF Page ${pageIndex + 1}",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            } ?: CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } ?: CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}