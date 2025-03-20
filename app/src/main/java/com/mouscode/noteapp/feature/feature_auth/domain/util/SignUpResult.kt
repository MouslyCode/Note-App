package com.mouscode.noteapp.feature.feature_auth.domain.util

sealed interface SignUpResult {
    data class Success(val username: String): SignUpResult
    data object Cancelled: SignUpResult
    data object Failure: SignUpResult
}