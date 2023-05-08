package com.example.geoapp.ui
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.geoapp.domain.model.FbUser
import com.example.geoapp.domain.utils.FacebookLoginUtils
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.login.LoginResult


class MainViewModel(
    private val facebookLoginUtils: FacebookLoginUtils
) : ViewModel() {

    val fbUserLiveData: LiveData<FbUser> = facebookLoginUtils.fbUserLiveData
    val callbackManager: CallbackManager = facebookLoginUtils.callbackManager
    val fbLoginCallback: FacebookCallback<LoginResult> = facebookLoginUtils

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) =
        facebookLoginUtils.onActivityResult(requestCode, resultCode, data)
}
