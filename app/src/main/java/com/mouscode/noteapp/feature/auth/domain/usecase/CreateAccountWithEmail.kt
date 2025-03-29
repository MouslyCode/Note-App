package com.mouscode.noteapp.feature.auth.domain.usecase

import com.mouscode.noteapp.feature.auth.domain.repository.AuthRepository

class CreateAccountWithEmail(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username:String, email: String, password: String) = repository.createAccountWithEmail(username,email, password)
}