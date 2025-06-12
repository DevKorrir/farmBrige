package dev.korryr.farmbrige.ui.features.auth.repo

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dev.korryr.farmbrige.ui.features.auth.dataModel.AuthService
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepository @Inject constructor(
    private val authService: AuthService,
    private val firestore: FirebaseFirestore
) {
    fun signUp(
        email: String,
        password: String
    ): Flow<Result<FirebaseUser>> = flow {
        emit(authService.signUp(email, password))
    }

    suspend fun login(
        email: String,
        password: String
    ): Result<FirebaseUser> {
        return authService.login(email, password)
    }

}