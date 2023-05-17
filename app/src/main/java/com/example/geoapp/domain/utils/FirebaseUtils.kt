package com.example.geoapp.domain.utils

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geoapp.data.repository.UserStatus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.geoapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.GoogleAuthProvider

class FirebaseUtils (private val context: Context) {

    private var auth : FirebaseAuth? = null
    private var googleSignInClient : GoogleSignInClient? = null
    val userStatusLiveData: LiveData<UserStatus>
        get() = _userStatusLiveData
    private var _userStatusLiveData = MutableLiveData<UserStatus>()

    fun issignin(): Boolean {
        return auth?.currentUser != null
    }

    fun createAccount(email: String) {
        val pass = "VeryHardPassword"
        if (email.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, pass)?.addOnCompleteListener {
                if (it.isSuccessful) {
                    _userStatusLiveData.postValue(UserStatus.REGISTERED)
                } else {
                    auth?.signInWithEmailAndPassword(email, pass)?.addOnCompleteListener {
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

    val signInIntent: Intent?
        get() {
            return googleSignInClient?.signInIntent
        }

    fun setup (activity: FragmentActivity) {
        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.resources.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

            googleSignInClient = GoogleSignIn.getClient(activity, gso)
    }

    fun handleResults(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            }
        }
        else {
            Toast.makeText(context, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth?.signInWithCredential(credential)?.addOnCompleteListener {
            if(it.isSuccessful) {
                Toast.makeText(context, GoogleSignIn.getLastSignedInAccount(context)?.email.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}