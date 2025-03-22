package com.mouscode.noteapp.feature.feature_auth.presentation

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mouscode.noteapp.feature.feature_auth.data.data_source.AccountManager
import com.mouscode.noteapp.ui.theme.NoteAppTheme
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val accountManager = remember {
        if (context is ComponentActivity){
            AccountManager(context)
        } else {
            null
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(24.dp))
        TextField(
            value = state.username,
            onValueChange = {
                onEvent(LoginEvent.onUsernameChange(it))
            },
            label = {
                Text(text = "Username")
            },
            placeholder = {Text("Username")},
            modifier = Modifier,
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = state.password,
            onValueChange = {
                onEvent(LoginEvent.onPasswordChange(it))
            },
            label = {
                Text(text = "Password")
            },
            placeholder = {Text("Password")},
            modifier = Modifier,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Registerd", style = MaterialTheme.typography.labelSmall.copy(Color.White))
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = state.isRegister,
                onCheckedChange = {
                    onEvent(LoginEvent.onToggleIsRegister)
                }
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                scope.launch {
                    if (state.isRegister){
                        val result = accountManager?.signUp(
                            username = state.username,
                            password = state.password
                        )
                        onEvent(LoginEvent.onSignUp(result))
                    }
                }
            }
        ) {
            Text( if(state.isRegister) "Login" else "Register")
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF202020)
@Composable
private fun LoginScreenPreview() {
    NoteAppTheme {
        LoginScreen(
            state = LoginState(),
            onEvent = {}
        )
    }
}