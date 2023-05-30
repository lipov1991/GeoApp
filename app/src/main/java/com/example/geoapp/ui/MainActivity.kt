package com.example.geoapp.ui

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.geoapp.R
import com.example.geoapp.databinding.ActivityAuthBinding
import com.example.geoapp.domain.model.FbUser
import com.facebook.login.LoginManager
import com.facebook.login.widget.LoginButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Wyswietlanie KeyHash
        try {
            val info = packageManager.getPackageInfo(
                "com.example.geoapp",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
        val sharedPreference =  getSharedPreferences("fbUsers", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()

        super.onCreate(savedInstanceState)
        //LoginManager.getInstance().logOut()
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_auth)
        val loginButton: LoginButton = findViewById(R.id.login_button)
        loginButton.setReadPermissions("email")
        loginButton.registerCallback(viewModel.callbackManager, viewModel.fbLoginCallback)
        //loginButton.performlogut() performlogout() is a protected method!
        val usernameTextView: TextView = findViewById(R.id.username_text_view)
        val emailTextView: TextView = findViewById(R.id.email)
        //shared preferences
        if (sharedPreference.contains("username")){
            usernameTextView.text = sharedPreference.getString("username","")
            emailTextView.text = sharedPreference.getString("mail","")
            editor.clear()
        }

        val fbObserver = Observer<FbUser> { newUser ->
            usernameTextView.text = newUser.name
            emailTextView.text = newUser.email
            //Log.d("accestoken:", newUser.token.toString())
            editor.putString("username",newUser.name)
            editor.putString("mail",newUser.email)
            editor.apply()
        }
        viewModel.fbUserLiveData.observe(this, fbObserver)
    }

}

