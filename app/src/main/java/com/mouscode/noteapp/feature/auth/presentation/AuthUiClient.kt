package com.mouscode.noteapp.feature.auth.presentation

import android.content.Context
import android.content.IntentSender
import android.credentials.GetCredentialRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.mouscode.noteapp.R
import com.mouscode.noteapp.feature.auth.presentation.util.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.security.MessageDigest
import java.util.UUID

class AuthUiClient (
    private val context: Context,
){
    private val auth = Firebase.auth

    fun createAccountWithEmail(email: String, password: String) : Flow<AuthResult> =
        callbackFlow{
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        trySend(AuthResult.Success)
                    } else {
                        trySend(AuthResult.Failure(message = task.exception?.message ?: "Unknown error"))
                    }
                }
        }

    fun loginWithEmail(email: String,password: String): Flow<AuthResult> = callbackFlow {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    trySend(AuthResult.Success)
                } else {
                    trySend(AuthResult.Failure(message = task.exception?.message ?: "Unknown error"))
                }
            }
    }

    fun createNonce(): String {
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)

        return digest.fold (""){str, it ->
            str + "%02x".format(it)
        }
    }

    fun signInWithGoogle(): Flow<AuthResult> = callbackFlow {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(R.string.web_client_id))
            .setAutoSelectEnabled(false)
            .setNonce(createNonce())
            .build()

        val request : GetCredentialRequest = Builder()
            .addCredentialOption(googleIdOption)
            .build()
    }
}