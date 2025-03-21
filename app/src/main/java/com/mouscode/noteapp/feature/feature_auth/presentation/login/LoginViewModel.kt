package com.mouscode.noteapp.feature.feature_auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mouscode.noteapp.feature.feature_auth.domain.util.SignUpResult


class LoginViewModel : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onEvent(event: LoginEvent) {
        when(event){
            is LoginEvent.onPasswordChange -> {
                state = state.copy(password = event.password)
            }
            is LoginEvent.onUsernameChange -> {
                state = state.copy(username = event.username)
            }
            is LoginEvent.onSignUp -> {
                when(event.result) {
                    is SignUpResult.Failure -> {
                        state = state.copy(
                            errorMessage = "Sign Up Failed"
                        )
                    }
                    is SignUpResult.Success -> {
                        state = state.copy(
                            loggedInUser = event.result.username
                        )
                    }
                    SignUpResult.Cancelled -> {
                        state = state.copy(
                            errorMessage = "Sign Up Cancelled"
                        )
                    }
                    null -> TODO()
                }
            }

            LoginEvent.onToggleIsRegister -> {
                state = state.copy(
                    isRegister = !state.isRegister
                )
            }
        }
    }
}