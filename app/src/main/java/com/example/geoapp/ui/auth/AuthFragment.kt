package com.example.geoapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.geoapp.R
import com.example.geoapp.domain.utils.ViewRouter
import com.example.geoapp.ui.map.MapFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


// Tu będzie formularz do logowania i rejestracji oraz przyciski do logowania za pośrednictwem Facebooka oraz Google'a.
class AuthFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModel()
    private val viewRouter: ViewRouter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.sign_in_button).setOnClickListener {
            viewRouter.navigateTo(MapFragment(), activity)
        }
    }
}
