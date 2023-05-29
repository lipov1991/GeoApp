package com.example.geoapp.domain.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geoapp.data.repository.UserStatus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseUtils {

    private val auth = Firebase.auth
    val userStatusLiveData: LiveData<UserStatus>
        get() = _userStatusLiveData

    private var _userStatusLiveData = MutableLiveData<UserStatus>()

    fun issignin(): Boolean {
        return auth.currentUser != null
    }

    fun createAccount(email: String) {
        val pass = "VeryHardPassword"
        if (email.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    _userStatusLiveData.postValue(UserStatus.REGISTERED)
                } else {
                    auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            _userStatusLiveData.postValue(UserStatus.LOGGED_IN)
                        } else {
                            _userStatusLiveData.postValue(UserStatus.WRONG_MAIL)
                        }
                    }
                }
            }
        } else {
            _userStatusLiveData.postValue(UserStatus.EMPTY_MAIL)
        }
    }
}