package com.example.geoapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.geoapp.R
import com.facebook.login.widget.LoginButton
import org.koin.androidx.viewmodel.ext.android.viewModel


// Tu będziemy decydować, co wyśweitlić jako pierwsze - ekran logowania czy główny ekran (w przypadku aktywnej sesji).
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        val loginButton: LoginButton = findViewById(R.id.login_button)
        loginButton.setReadPermissions("email")
        loginButton.registerCallback(viewModel.callbackManager, viewModel.fbLoginCallback)
        // TODO Listen for facebook user here...
        val usernameTextView: TextView = findViewById(R.id.username_text_view)
        val emailTextView: TextView = findViewById(R.id.email)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onActivityResult(requestCode, resultCode, data)
    }
}
