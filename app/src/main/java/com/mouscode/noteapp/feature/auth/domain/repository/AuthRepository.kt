package com.mouscode.noteapp.feature.auth.domain.repository

import com.mouscode.noteapp.feature.auth.presentation.util.AuthResponse

interface AuthRepository {
    suspend fun createAccountWithEmail(email: String,password: String): AuthResponse
    suspend fun signInWithEmail(email: String, password: String): AuthResponse
    suspend fun signInWithGoogle(email: String, password: String): AuthResponse
    suspend fun SignOut(): AuthResponse
}