package com.example.geoapp.domain.utils

import androidx.lifecycle.MutableLiveData
import com.example.geoapp.data.repository.UserStatus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseUtils {

    private val auth = Firebase.auth

    val userStatusLiveData: MutableLiveData<UserStatus>
        get() = _userStatusLiveData
    private var _userStatusLiveData = MutableLiveData<UserStatus>()

    fun issignin(): Boolean {
        return auth.currentUser != null
    }

    fun createAccount(email: String) {
        println(email)
        val pass = "VeryHardPassword"
        if (email.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    userStatusLiveData.postValue(UserStatus.REGISTERED)
                } else {
                    println("Rejestracja nieudana - logowanie")
                    auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            userStatusLiveData.postValue(UserStatus.LOGGED_IN)
                        } else {
                            userStatusLiveData.postValue(UserStatus.WRONG_MAIL)
                        }
                    }
                }
            }
        } else {
            userStatusLiveData.postValue(UserStatus.EMPTY_MAIL)
        }
    }
}