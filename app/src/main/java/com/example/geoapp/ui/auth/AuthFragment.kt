package com.example.geoapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.geoapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel


// Tu będzie formularz do logowania i rejestracji oraz przyciski do logowania za pośrednictwem Facebooka oraz Google'a.
class AuthFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_login, container, false)
}
