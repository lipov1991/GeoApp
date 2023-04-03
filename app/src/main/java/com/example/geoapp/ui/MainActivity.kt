package com.example.geoapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.geoapp.R
import com.example.geoapp.ui.auth.AuthViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel


// Tu będziemy decydować, co wyśweitlić jako pierwsze - ekran logowania czy główny ekran (w przypadku aktywnej sesji).
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val viewModel: MainViewModel by viewModel()
    private val authviewmodel: AuthViewModel by viewModel()
    private lateinit var loginButton: LoginButton
    private lateinit var usernameTextView: TextView
    private lateinit var emailTextView: TextView
    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        callbackManager = CallbackManager.Factory.create()
        loginButton = findViewById(R.id.login_button)
        usernameTextView = findViewById(R.id.username_text_view)
        emailTextView = findViewById(R.id.email)
        loginButton.setReadPermissions("email")
        loginButton.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {

            override fun onCancel() {
                Log.d(TAG, "Login canceled")
            }

            override fun onError(error: FacebookException) {
                Log.e(TAG, "Facebook login error: ${error.message}")
            }

            override fun onSuccess(result: LoginResult) {
                Log.d(TAG, "Facebook login success. Access token: ${result.accessToken}")
                val request =
                    GraphRequest.newMeRequest(result.accessToken) { jsonObject, response ->
                        Log.d(TAG, "jsonObject: ${jsonObject.toString()}")
                        Log.d(TAG, "response: ${response.toString()}")
                        val email = jsonObject?.optString("email")
                        val name = jsonObject?.optString("name")
                        Log.d(TAG, "User info: email: $email; name: $name; error: ${response?.error}")
                        usernameTextView.text = "NAME: ${name} "
                        emailTextView.text = "EMAIL: ${email} "
                    }
                val parameters = Bundle()
                parameters.putString("fields", "id,name,email")
                request.parameters = parameters
                request.executeAsync()
            }
        })

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
