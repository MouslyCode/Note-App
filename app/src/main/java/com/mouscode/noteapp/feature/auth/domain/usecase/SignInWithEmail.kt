package com.mouscode.noteapp.feature.auth.domain.usecase

import com.mouscode.noteapp.feature.auth.domain.repository.AuthRepository

class SignInWithEmail(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) = repository.signInWithEmail(email, password)
}