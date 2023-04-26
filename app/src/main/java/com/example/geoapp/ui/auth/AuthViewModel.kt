package com.example.geoapp.ui.auth

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.geoapp.domain.utils.FirebaseAuthUtils
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task


// Tutaj będzie logika związana z AuthFragment.
class AuthViewModel(
    private val firebaseAuthUtils: FirebaseAuthUtils

) : ViewModel() {

    val signInIntent: Intent?
        get() = firebaseAuthUtils.signInIntent
    var userName = ""

    fun setupFirebase(activity: FragmentActivity){
        firebaseAuthUtils.setup(activity)
    }

    fun handleLoginResult (task: Task<GoogleSignInAccount>) {
        firebaseAuthUtils.handleResults(task)
    }

    fun signUp() {
        // TODO
    }
}
