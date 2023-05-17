package com.example.geoapp.domain.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.geoapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class FirebaseAuthUtils (private val context: Context) {

    private var auth : FirebaseAuth? = null
    private var googleSignInClient : GoogleSignInClient? = null
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
