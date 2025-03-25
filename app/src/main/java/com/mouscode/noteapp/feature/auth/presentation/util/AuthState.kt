package com.mouscode.noteapp.feature.auth.presentation.util

data class AuthState (
    val authenticated: Boolean = false,
    val error: String? = null
)