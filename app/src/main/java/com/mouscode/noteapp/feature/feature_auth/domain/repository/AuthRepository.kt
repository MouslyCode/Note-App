package com.mouscode.noteapp.feature.feature_auth.domain.repository

import com.mouscode.noteapp.feature.feature_auth.domain.util.SignUpResult

interface AuthRepository {

    suspend fun signUp(username: String, password: String): SignUpResult

}