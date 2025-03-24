package com.mouscode.noteapp.feature.auth.domain.usecase

import com.mouscode.noteapp.feature.auth.domain.repository.AuthRepository

class SignInWithGoogle(
    private val repository: AuthRepository
) {
    suspend fun invoke(email: String, password: String) = repository.signInWithGoogle(email, password)
}