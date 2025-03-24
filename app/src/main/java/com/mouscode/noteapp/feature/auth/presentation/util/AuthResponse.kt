package com.mouscode.noteapp.feature.auth.presentation.util

sealed interface AuthResponse {
    data object Success: AuthResponse
    data class Failure(val message: String): AuthResponse
}