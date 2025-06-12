package dev.korryr.farmbrige.ui.features.auth.dataModel

import com.google.firebase.auth.FirebaseUser

interface AuthService {

    suspend fun signUp(
        email: String,
        password: String
    ) : Result<FirebaseUser>

    suspend fun login(
        email: String,
        password: String
    ): Result<FirebaseUser>
}