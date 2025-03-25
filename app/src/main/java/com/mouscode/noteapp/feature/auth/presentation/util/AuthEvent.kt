package com.mouscode.noteapp.feature.auth.presentation.util

sealed class AuthEvent {
    data class onSignInWithGoogle(val email: String, val password: String): AuthEvent()
    data class onSignInWithEmail(val email: String, val password: String): AuthEvent()
    data class onCreateAccontWithEmail(val email: String, val password: String): AuthEvent()
    object onSignOut: AuthEvent()
}