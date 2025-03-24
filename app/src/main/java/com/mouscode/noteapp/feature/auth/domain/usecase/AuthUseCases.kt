package com.mouscode.noteapp.feature.auth.domain.usecase

data class AuthUseCases(
    val signInWithGoogle: SignInWithGoogle,
    val signInWithEmail: SignInWithEmail,
    val createAccountWithEmail: CreateAccountWithEmail,
    val signOut: SignOut
)