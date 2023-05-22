package com.example.geoapp.ui.auth

import androidx.lifecycle.ViewModel
import com.example.geoapp.data.repository.AuthRepository


// Tutaj będzie logika związana z AuthFragment.
class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    var userName = ""

    fun signIn() {
        // TODO
    }

    fun signUp() {
        // TODO
    }
}