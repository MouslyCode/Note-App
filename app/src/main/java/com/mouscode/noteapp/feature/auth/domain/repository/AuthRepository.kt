package com.mouscode.noteapp.feature.auth.domain.repository

import com.mouscode.noteapp.feature.auth.data.repository.util.AuthResponse
import com.mouscode.noteapp.feature.auth.domain.model.User

interface AuthRepository {
    suspend fun createAccountWithEmail(username:String,email: String,password: String): AuthResponse
    suspend fun signInWithEmail(email: String, password: String): AuthResponse
    suspend fun signInWithGoogle(email: String, password: String): AuthResponse
    suspend fun signOut(): AuthResponse
    suspend fun getUser() : User?
}