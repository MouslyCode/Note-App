package com.mouscode.noteapp.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.mouscode.noteapp.feature.auth.data.repository.AuthRepositoryImpl
import com.mouscode.noteapp.feature.auth.domain.repository.AuthRepository
import com.mouscode.noteapp.feature.auth.domain.usecase.AuthUseCases
import com.mouscode.noteapp.feature.auth.domain.usecase.CreateAccountWithEmail
import com.mouscode.noteapp.feature.auth.domain.usecase.SignInWithEmail
import com.mouscode.noteapp.feature.auth.domain.usecase.SignInWithGoogle
import com.mouscode.noteapp.feature.auth.domain.usecase.SignOut
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(context: Context,firebaseAuth: FirebaseAuth): AuthRepository =
        AuthRepositoryImpl(context,firebaseAuth,)

    @Provides
    @Singleton
    fun provideAuthUseCase(repository: AuthRepository) : AuthUseCases =
        AuthUseCases(
        signInWithGoogle = SignInWithGoogle(repository),
        signInWithEmail = SignInWithEmail(repository),
        createAccountWithEmail = CreateAccountWithEmail(repository),
        signOut = SignOut(repository)
    )



}