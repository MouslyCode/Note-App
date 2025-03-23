package com.mouscode.noteapp.feature.auth.presentation.util

sealed interface AuthResult {
    data object Success: AuthResult
    data class Failure(val message: String): AuthResult
}