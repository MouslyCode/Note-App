package com.mouscode.noteapp.feature.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mouscode.noteapp.ui.theme.NoteAppTheme
import com.mouscode.noteapp.util.Screen

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    NoteAppTheme {
        Scaffold { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = "Email",
                    onValueChange = {}
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = "Password",
                    onValueChange = {},
                    suffix  = {
                        Icon(imageVector = Icons.Outlined.Visibility, contentDescription = null)
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Login")
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Icon(imageVector = Icons.Outlined.VerifiedUser, contentDescription = null)
                        Spacer(modifier = Modifier.width(24.dp))
                        Text(text = "Sign In with Google")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoginScrenPreview() {
    NoteAppTheme {
        LoginScreen()
    }
}
