package com.example.geoapp.domain.utils

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geoapp.domain.model.FbUser
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginResult


class FacebookLoginUtils : FacebookCallback<LoginResult> {

    companion object {
        private const val TAG = "FBLoginUtils"
    }

    val callbackManager = CallbackManager.Factory.create()
    val fbUserLiveData: LiveData<FbUser>
        get() = _fbUserLiveData
    private var _fbUserLiveData = MutableLiveData<FbUser>()

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
                if (email.isNullOrBlank()) {
                    Log.e(TAG, "Unknown email")
                    return@newMeRequest
                }
                val name = jsonObject.optString("name")
                if (name.isNullOrBlank()) {
                    Log.e(TAG, "Unknown name")
                    return@newMeRequest
                }
                Log.d(TAG, "User info: email: $email; name: $name; error: ${response?.error}")
                val fbUser = FbUser(name, email)
                _fbUserLiveData.value = fbUser
            }
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email")
        request.parameters = parameters
        request.executeAsync()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
