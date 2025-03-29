package com.mouscode.noteapp.feature.auth.domain.model

data class User(
    val uid: String,
    val email: String,
    val displayName: String?,
    val photoUrl: String?
)
