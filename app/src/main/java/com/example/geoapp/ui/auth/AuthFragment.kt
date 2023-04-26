package com.example.geoapp.ui.auth

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.geoapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import org.koin.androidx.viewmodel.ext.android.viewModel


// Tu będzie formularz do logowania i rejestracji oraz przyciski do logowania za pośrednictwem Facebooka oraz Google'a.
class AuthFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModel()

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            viewModel.handleLoginResult(task)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let{
            viewModel.setupFirebase(it)
        }

        view.findViewById<Button>(R.id.btngoogle).setOnClickListener {
            viewModel.signInIntent?.let {
                launcher.launch(it)
            }
        }

    }
}