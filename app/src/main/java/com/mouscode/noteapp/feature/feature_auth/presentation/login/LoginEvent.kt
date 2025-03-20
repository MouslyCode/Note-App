package com.mouscode.noteapp.feature.feature_auth.presentation.login

import com.mouscode.noteapp.feature.feature_auth.domain.util.SignUpResult

sealed interface LoginEvent {

    data class onSignUp(val result: SignUpResult?): LoginEvent
    data class onUsernameChange(val username: String): LoginEvent
    data class onPasswordChange(val password: String): LoginEvent
    data object onToggleIsRegister: LoginEvent

}