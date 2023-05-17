package com.example.geoapp.ui.auth

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.example.geoapp.data.repository.UserStatus
import com.example.geoapp.domain.utils.FirebaseUtils


class AuthViewModel(private val FirebaseUtils: FirebaseUtils) : ViewModel() {

    val signInIntent: Intent?
        get() = FirebaseUtils.signInIntent
    var userName = ""
    val userStatusLiveData: LiveData<UserStatus> = FirebaseUtils.userStatusLiveData

    fun signin(email: String) = FirebaseUtils.createAccount(email)

    fun issignin() = FirebaseUtils.issignin()
    fun setupFirebase(activity: FragmentActivity){
        FirebaseUtils.setup(activity)
    }

    fun handleLoginResult (task: Task<GoogleSignInAccount>) {
        FirebaseUtils.handleResults(task)
    }

}
