package com.mouscode.noteapp.feature.auth.domain.usecase

import com.mouscode.noteapp.feature.auth.domain.repository.AuthRepository

class SignOut(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.SignOut()
}