package com.mouscode.noteapp.feature.auth.presentation

import android.content.Context
import android.content.IntentSender
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.mouscode.noteapp.R
import com.mouscode.noteapp.feature.auth.presentation.util.AuthResult
import com.mouscode.noteapp.feature.feature_auth.domain.util.SignInResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
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

    fun signInWithEmail(email: String,password: String): Flow<AuthResult> = callbackFlow {
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

    suspend fun signInWithGoogle(): AuthResult {
        try {
            val credentialManager = CredentialManager.create(context)

            val request = GetCredentialRequest(
                listOf(
                    GetGoogleIdOption.Builder()
                        .setServerClientId(context.getString(R.string.web_client_id))
                        .setFilterByAuthorizedAccounts(false)
                        .setNonce(createNonce())
                        .build()
                )
            )

            val result = credentialManager.getCredential(
                request = request,
                context = context
            )
            val credential = result.credential
            if (credential is CustomCredential &&
                credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                try {
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                    val idToken = googleIdTokenCredential.idToken

                    //Sign In with firebase
                    val firebaseCredential = GoogleAuthProvider.getCredential(idToken,null)
                    val authResult = auth.signInWithCredential(firebaseCredential).await()

                    return if (authResult.user != null){
                        AuthResult.Success
                    } else {
                        AuthResult.Failure(message = "User Not Found")
                    }
                } catch (e: GoogleIdTokenParsingException) {
                    return AuthResult.Failure(message = e.message ?: "Unknown error")
                }
            } else {
                return AuthResult.Failure(message = "Unexpected credential type")
            }
        } catch (e: GetCredentialException){
            return AuthResult.Failure(message = e.message ?: "Credential Manager Error")
        } catch (e: Exception){
            return  AuthResult.Failure(message = e.message ?: "Unknown error")
        }
    }
}