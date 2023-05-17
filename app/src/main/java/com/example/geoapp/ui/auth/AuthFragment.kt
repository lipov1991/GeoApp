package com.example.geoapp.ui.auth

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.geoapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.example.geoapp.data.repository.UserStatus
import com.example.geoapp.databinding.FragmentLoginBinding
import com.example.geoapp.ui.map.MapFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthFragment : Fragment() {
    private val viewModel: AuthViewModel by viewModel()
    
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            viewModel.handleLoginResult(task)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUserStatusLiveData()
        binding.LoginButton.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            viewModel.signin(email)
        }

        activity?.let{
            viewModel.setupFirebase(it)
        }

        view.findViewById<Button>(R.id.Google_Button).setOnClickListener {
            viewModel.signInIntent?.let {
                launcher.launch(it)
            }
        }
    }

    private fun observeUserStatusLiveData() {
        viewModel.userStatusLiveData.observe(::getLifecycle) {
            when (it) {
                UserStatus.LOGGED_IN -> {
                    Toast.makeText(requireContext(), "Pomyślnie zalogowano", Toast.LENGTH_SHORT)
                        .show()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MapFragment()).commit()
                }
                UserStatus.REGISTERED -> {
                    Toast.makeText(requireContext(), "Pomyślnie zarejestrowano", Toast.LENGTH_SHORT)
                        .show()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MapFragment()).commit()
                }
                UserStatus.EMPTY_MAIL -> {
                    Toast.makeText(requireContext(), "Uzupełnij adres E-Mail", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    Toast.makeText(requireContext(), "Niepoprawny adres E-Mail", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (viewModel.issignin()) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MapFragment()).commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
