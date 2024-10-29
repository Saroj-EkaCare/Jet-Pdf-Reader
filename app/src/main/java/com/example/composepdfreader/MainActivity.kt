package com.example.composepdfreader

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composepdfreader.ui.theme.ComposePdfReaderTheme
import com.example.reader.PdfReaderManager
import com.example.reader.presentation.states.PdfSource

class MainActivity : ComponentActivity() {

    private lateinit var pdfReaderManager: PdfReaderManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        pdfReaderManager = PdfReaderManager(this)

        setContent {
            ComposePdfReaderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PdfReaderScreen(
                        modifier = Modifier.padding(innerPadding),
                        pdfReaderManager = pdfReaderManager
                    )
                }
            }
        }
    }
}

@Composable
fun PdfReaderScreen(
    modifier: Modifier = Modifier,
    pdfReaderManager: PdfReaderManager
) {
    var pdfSource by remember { mutableStateOf<PdfSource?>(null) }

    // File picker launcher
    val pickPdfLauncher = rememberLauncherForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
        uri?.let {
            pdfSource = PdfSource.Uri(it)
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            pickPdfLauncher.launch(arrayOf("application/pdf"))
        }) {
            Text("Load PDF from URI")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            pdfSource = PdfSource.Url("https://www.sldttc.org/allpdf/21583473018.pdf")
        }) {
            Text("Load PDF from URL")
        }

        pdfSource?.let {
            pdfReaderManager.PdfViewer(
                pdfSource = it,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}