package com.example.paddel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    val signedIn: StateFlow<Boolean> = repository.signedIn

    fun signIn() {
        viewModelScope.launch { repository.setSignedIn(true) }
    }
}
