package com.example.speechtotext.ui

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.speechtotext.R
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    viewModel: AppScreenViewModel,
    onStartForResult: ActivityResultLauncher<Intent>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Speech to Text",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) { paddingValues ->
        SpeechToTextScreen(
            viewModel.outputText.value,
            onStartForResult,
            modifier.padding(paddingValues)
        )
    }
}

@Composable
fun SpeechToTextScreen(
    textValue: String,
    onStartForResult: ActivityResultLauncher<Intent>,
    modifier: Modifier
){
    val context = LocalContext.current

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tap the mic icon to launch Speech to text",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp, pressedElevation = 0.dp, disabledElevation = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = { getSpeechInput(onStartForResult, context = context) },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_mic),
                contentDescription = "Microphone",
                tint = Color.Green,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .padding(5.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = textValue,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

private fun getSpeechInput(onStartForResult: ActivityResultLauncher<Intent> ,context: Context) {
    // Check if speech recognizer intent is present or not.
    if (!SpeechRecognizer.isRecognitionAvailable(context)) {
        // if the intent is not present we are simply displaying a toast message.
        Toast.makeText(context, "Speech not Available", Toast.LENGTH_SHORT).show()
    } else {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH
        )

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something...")

        onStartForResult.launch(intent)
    }
}

