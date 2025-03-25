package com.mouscode.noteapp.feature.auth.data.repository.util

sealed interface AuthResponse {
    data object Success: AuthResponse
    data class Failure(val message: String): AuthResponse
}