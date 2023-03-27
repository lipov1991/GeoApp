package com.example.geoapp.ui.auth

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.geoapp.data.repository.AuthRepository
import com.facebook.*
import com.facebook.login.BuildConfig
import com.facebook.login.LoginResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.geoapp.R
import com.example.geoapp.databinding.FragmentLoginBinding
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.net.ssl.SSLSessionBindingEvent

// Tutaj będzie logika związana z AuthFragment.
class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    lateinit var loginBinding: FragmentLoginBinding
    lateinit var callbackManager: CallbackManager

    fun signIn() {
        //TODO
    }
    fun signUp() {
        // TODO
    }

}

