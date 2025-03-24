package com.mouscode.noteapp.feature.auth.domain.usecase

import com.mouscode.noteapp.feature.auth.domain.repository.AuthRepository

class CreateAccountWithEmail(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) = repository.createAccountWithEmail(email, password)
}