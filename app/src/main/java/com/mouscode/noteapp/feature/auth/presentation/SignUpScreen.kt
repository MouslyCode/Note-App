package com.mouscode.noteapp.feature.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mouscode.noteapp.R
import com.mouscode.noteapp.ui.theme.NoteAppTheme

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    var isVisible by remember { mutableStateOf(false) }
    NoteAppTheme {
        Scaffold { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

                ) {
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(45.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .defaultMinSize(minWidth = 305.dp),
                    value = "",
                    placeholder = {
                        Text(text = "Username")
                    },
                    onValueChange = {}
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .defaultMinSize(minWidth = 305.dp),
                    value = "",
                    placeholder = {
                        Text(text = "Email")
                    },
                    onValueChange = {}
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column {
                    OutlinedTextField(
                        modifier = Modifier
                            .defaultMinSize(minWidth = 305.dp),
                        value = "",
                        placeholder = {
                            Text(
                                text = "Password",
                            )
                        },
                        onValueChange = {},
                        trailingIcon = {
                            IconButton(onClick = { isVisible = !isVisible }) {
                                if (isVisible) Icon(
                                    imageVector = Icons.Outlined.Visibility,
                                    contentDescription = null
                                )
                                else Icon(
                                    imageVector = Icons.Outlined.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        },
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 45.dp),
                    onClick = { },
                    shape = RoundedCornerShape(2.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                ) {
                    Text(
                        text = "Sign Up",
                        style = MaterialTheme.typography.labelLarge.copy(color = Color.Black)
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 45.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 45.dp),
                    shape = RoundedCornerShape(2.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.DarkGray
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google_logo),
                            contentDescription = "google logo",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Sign Up with Google",
                            style = MaterialTheme.typography.labelLarge.copy(color = Color.White)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview(modifier: Modifier = Modifier) {
    NoteAppTheme {
        SignUpScreen()
    }
}