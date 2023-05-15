package com.example.geoapp.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.geoapp.R
import com.example.geoapp.databinding.ActivityAuthBinding
import com.example.geoapp.domain.model.FbUser
import com.facebook.login.widget.LoginButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_auth)
        val loginButton: LoginButton = findViewById(R.id.login_button)
        loginButton.setReadPermissions("email")
        loginButton.registerCallback(viewModel.callbackManager, viewModel.fbLoginCallback)
        val usernameTextView: TextView = findViewById(R.id.username_text_view)
        val emailTextView: TextView = findViewById(R.id.email)
        val fbObserver = Observer<FbUser> { newUser ->
            usernameTextView.text = newUser.name
            emailTextView.text = newUser.email
        }
        viewModel.fbUserLiveData.observe(this, fbObserver)
    }
}

