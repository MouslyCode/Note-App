package com.mouscode.noteapp.feature.auth.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mouscode.noteapp.ui.theme.NoteAppTheme

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    NoteAppTheme {
        Scaffold { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {

            }
        }
    }
}