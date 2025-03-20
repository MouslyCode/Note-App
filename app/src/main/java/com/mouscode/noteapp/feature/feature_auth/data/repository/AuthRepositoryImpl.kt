package com.mouscode.noteapp.feature.feature_auth.data.repository

import android.app.Activity
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager
import androidx.credentials.exceptions.CreateCredentialCancellationException
import androidx.credentials.exceptions.CreateCredentialException
import com.mouscode.noteapp.feature.feature_auth.domain.repository.AuthRepository
import com.mouscode.noteapp.feature.feature_auth.domain.util.SignUpResult

class AuthRepositoryImpl (
    private val activity: Activity
) : AuthRepository{

    private val credentialManager = CredentialManager.create(activity)

    override suspend fun signUp(
        username: String,
        password: String
    ): SignUpResult {
        return try {
            credentialManager.createCredential(
                context = activity,
                request = CreatePasswordRequest(
                    id = username, password = password
                )
            )
            SignUpResult.Success(username)
        } catch (e: CreateCredentialCancellationException){
            e.printStackTrace()
            SignUpResult.Cancelled
        } catch (e: CreateCredentialException){
            e.printStackTrace()
            SignUpResult.Failure
        }
    }
}