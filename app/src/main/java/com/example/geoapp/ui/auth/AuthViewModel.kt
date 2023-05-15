package com.example.geoapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.geoapp.data.repository.UserStatus
import com.example.geoapp.domain.utils.FirebaseUtils


class AuthViewModel(private val FirebaseUtils: FirebaseUtils) : ViewModel() {

    val userStatusLiveData: LiveData<UserStatus> = FirebaseUtils.userStatusLiveData

    fun signin(email: String) = FirebaseUtils.createAccount(email)

    fun issignin() = FirebaseUtils.issignin()

}
