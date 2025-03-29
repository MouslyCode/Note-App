package com.mouscode.noteapp.feature.auth.data.repository.util

import com.mouscode.noteapp.feature.auth.domain.model.User

sealed interface AuthResponse {
    data object Success: AuthResponse
    data class AuntenticatedUser(val user: User): AuthResponse
    data class Failure(val message: String): AuthResponse
}