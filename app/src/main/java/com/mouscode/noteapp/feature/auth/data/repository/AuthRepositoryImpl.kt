package com.mouscode.noteapp.feature.auth.data.repository

import android.content.Context
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.mouscode.noteapp.R
import com.mouscode.noteapp.feature.auth.domain.repository.AuthRepository
import com.mouscode.noteapp.feature.auth.data.repository.util.AuthResponse
import com.mouscode.noteapp.feature.auth.domain.model.User
import kotlinx.coroutines.tasks.await
import java.security.MessageDigest
import java.util.UUID

class AuthRepositoryImpl(
    private val context: Context,
    private val auth: FirebaseAuth,
    private val credentialManager: CredentialManager = CredentialManager.create(context)
) : AuthRepository {

    private fun createNonce(): String {
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)

        return digest.fold("") { str, it ->
            str + "%02x".format(it)
        }
    }

    override suspend fun createAccountWithEmail(
        username: String,
        email: String,
        password: String
    ): AuthResponse {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email,password).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null) {
                AuthResponse.AuntenticatedUser(
                    user = User(
                        uid = firebaseUser.uid.toString(),
                        email = firebaseUser.email.toString(),
                        displayName = firebaseUser.displayName,
                        photoUrl = firebaseUser.photoUrl.toString()
                    )
                )
                AuthResponse.Success
            }
            else AuthResponse.Failure(message = "User is already registered")
        } catch (e: Exception){
            e.printStackTrace()
            AuthResponse.Failure(message = e.message ?: "Unknown error")
        }
    }

    override suspend fun signInWithEmail(
        email: String,
        password: String
    ): AuthResponse {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val firebaseUser = authResult.user
            if (firebaseUser != null) {
                AuthResponse.AuntenticatedUser(
                    user = User(
                        uid = firebaseUser.uid.toString(),
                        email = firebaseUser.email.toString(),
                        displayName = firebaseUser.displayName,
                        photoUrl = firebaseUser.photoUrl.toString()
                    )
                )
                AuthResponse.Success
            }
            else AuthResponse.Failure(message = "Firebase Authentication Failed")
        } catch (e: Exception){
            e.printStackTrace()
            AuthResponse.Failure(message = e.message ?: "Unknown error")
        }
    }

    override suspend fun signInWithGoogle(
        email: String,
        password: String
    ): AuthResponse {
        return try {
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
                context = context,
                request = request
            )

            val credential = result.credential
            if(credential is CustomCredential &&
                credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){

                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val idToken = googleIdTokenCredential.idToken

                val firebaseCredential = GoogleAuthProvider.getCredential(idToken,null)
                val authResult = auth.signInWithCredential(firebaseCredential).await()
                val firebaseUser = authResult.user

                if(firebaseUser != null) {
                    AuthResponse.AuntenticatedUser(
                        user = User(
                            uid = firebaseUser.uid.toString(),
                            email = firebaseUser.email.toString(),
                            displayName = firebaseUser.displayName,
                            photoUrl = firebaseUser.photoUrl.toString()
                        )
                    )
                    AuthResponse.Success
                }
                else AuthResponse.Failure(message = "Firebase Authentication Failed")
            } else {
                AuthResponse.Failure(message = "Unexpected Credential Type")
            }

        } catch (e: GetCredentialException) {
            e.printStackTrace()
            AuthResponse.Failure(message = e.message ?: "Credential Manager Error")
        } catch (e: Exception) {
            e.printStackTrace()
            AuthResponse.Failure(message = e.message ?: "Unknown Error")
        }
    }



    override suspend fun signOut(): AuthResponse {
        return try {
            auth.signOut()
            try {
                val clearRequest = ClearCredentialStateRequest()
                credentialManager.clearCredentialState(clearRequest)
            } catch (e: Exception){
                Log.w("AuthRepository", "Failed to clear credential state: ${e.message}")
            }

            AuthResponse.Success
        } catch (e: Exception) {
            e.printStackTrace()
            AuthResponse.Failure(message = e.message ?: "Unknown Error")
        }
    }

    override suspend fun getUser(): User? {
        val firebaseUser = auth.currentUser
        return firebaseUser?.let {
            User(
                uid = it.uid,
                email = it.email.toString(),
                displayName = it.displayName,
                photoUrl = it.photoUrl.toString(),
            )
        }
    }
}