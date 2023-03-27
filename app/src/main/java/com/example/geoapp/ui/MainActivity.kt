package com.example.geoapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.viewModelScope
import com.example.geoapp.R
import com.example.geoapp.databinding.FragmentLoginBinding
import com.example.geoapp.ui.auth.AuthFragment
import com.example.geoapp.ui.auth.AuthViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult
import kotlinx.coroutines.isActive
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel


// Tu będziemy decydować, co wyśweitlić jako pierwsze - ekran logowania czy główny ekran (w przypadku aktywnej sesji).
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val viewModel: MainViewModel by viewModel()
    private val authviewmodel: AuthViewModel by viewModel()

    private fun getFacebookData(obj: JSONObject?) {
        val name = obj?.getString("name")
        val email = obj?.getString("email")
        val totalcount = obj?.getJSONObject("friends")?.getJSONObject("summary")?.
        getString("total_count")
//        print(obj)
//        println(name)
//        println(totalcount)
        authviewmodel.loginBinding.Name.text = "NAME: ${name} "
        authviewmodel.loginBinding.email.text = "EMAIL: ${email} "
        authviewmodel.loginBinding.friends.text = "NO. FRIENDS: ${totalcount} "
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, AuthFragment()).commit()
        //authviewmodel.loginBinding = DataBindingUtil.setContentView(this,com.example.geoapp.R.layout.fragment_login)
        authviewmodel.loginBinding = FragmentLoginBinding.inflate(layoutInflater)
        authviewmodel.callbackManager = CallbackManager.Factory.create()
        authviewmodel.loginBinding.loginButton.setReadPermissions(listOf("email","public_profile","user_friends"))
        authviewmodel.loginBinding.loginButton.registerCallback(authviewmodel.callbackManager,object :
            FacebookCallback<LoginResult> {
            override fun onCancel() {
                println("canceled") //nie dziala
            }

            override fun onError(error: FacebookException) {
                TODO("Not yet implemented")
            }

            override fun onSuccess(result: LoginResult) {
                val graphRequest =
                    GraphRequest.newMeRequest(result.accessToken) { jsonObject, response ->
                        Log.d(TAG, "jsonObject: ${jsonObject.toString()}")
                        Log.d(TAG, "response: ${response.toString()}")
                        getFacebookData(jsonObject)
                    }
                val parameters = Bundle()
                parameters.putString("fields","id,email,friends,name")
                graphRequest.parameters = parameters
                graphRequest.executeAsync()
            }
        })

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        authviewmodel.callbackManager.onActivityResult(requestCode,resultCode,data)
    }
}
