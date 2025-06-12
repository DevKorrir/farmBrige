package dev.korryr.farmbrige.ui.features.auth.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import dev.korryr.farmbrige.ui.features.auth.repo.AuthRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val user: FirebaseUser) : AuthUiState()

    //data class SuccessWithRole(val user: FirebaseUser, val role: String) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
    object PasswordResetSent : AuthUiState()
}

class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _authstate = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val authState: StateFlow<AuthUiState> = _authstate

    fun signUp(email: String, password: String, username: String) {
        if (email.isBlank() && password.isBlank() && username.isBlank()) {
            _authstate.value = AuthUiState.Error("Usrename, Email and password cannot be empty")
            return
        }

        _authstate.value = AuthUiState.Loading
        viewModelScope.launch {
            authRepository.signUp(email, password).collect { result ->
                result.fold(onSuccess = { user ->
                    user.updateProfile(
                        UserProfileChangeRequest.Builder()
                            .setDisplayName(username)
                            .build()
                    )?.addOnCompleteListener { upd ->
                        if (upd.isSuccessful) {
                            _authstate.value = AuthUiState.Success(user)
                        } else {
                            _authstate.value =
                                AuthUiState.Error(upd.exception?.message ?: "Unknown error")
                        }

                    }
                }, onFailure = {
                    _authstate.value = AuthUiState.Error(it.message ?: "Unknown error")
                })
            }

        }
    }

}