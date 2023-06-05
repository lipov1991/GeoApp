package com.example.geoapp.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.geoapp.R
import com.example.geoapp.data.repository.UserStatus
import com.example.geoapp.databinding.FragmentLoginBinding
import com.example.geoapp.domain.utils.LocationHandler
import com.example.geoapp.domain.utils.PermissionUtils
import com.example.geoapp.domain.utils.PointFingerprints
import com.example.geoapp.ui.map.MapFragment
import org.koin.android.ext.android.inject

class AuthFragment : Fragment() {

    companion object {
        private const val REQUEST_CODE_LOCATION_PERMISSIONS = 1
    }

    private val permissionUtils: PermissionUtils by inject()
    private val locationHandler: LocationHandler by inject()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    //    private val auth: FirebaseAuth
//        get() = Firebase.auth
    private val userStatusLiveData: LiveData<UserStatus>
        get() = _userStatusLiveData
    private var _userStatusLiveData = MutableLiveData<UserStatus>()

//    fun issignin(): Boolean {
//        return auth.currentUser != null
//    }

    fun createAccount(email: String) {
//        val pass = "VeryHardPassword"
//        if (email.isNotEmpty()) {
//            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
//                if (it.isSuccessful) {
//                    _userStatusLiveData.postValue(UserStatus.REGISTERED)
//                } else {
//                    auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
//                        if (it.isSuccessful) {
//                            _userStatusLiveData.postValue(UserStatus.LOGGED_IN)
//                        } else {
//                            _userStatusLiveData.postValue(UserStatus.WRONG_MAIL)
//                        }
//                    }
//                }
//            }
//        } else {
//            _userStatusLiveData.postValue(UserStatus.EMPTY_MAIL)
//        }
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
            createAccount(email)
        }
        permissionUtils.requestPermissionIfNeeded(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            REQUEST_CODE_LOCATION_PERMISSIONS,
            requireActivity()
        )
        fingerprintTest()
    }

    private fun fingerprintTest() {
        locationHandler.sortFingerprints()
        val groupedRouters: List<PointFingerprints> = locationHandler.getAverageRouterPer4Directions()
        val closestRoom: String = locationHandler.calculateDistance(groupedRouters, locationHandler.routers)
        Log.d("TestData", String.format("Najblizszy pokój: %s", closestRoom))
    }

    private fun observeUserStatusLiveData() {
        userStatusLiveData.observe(this::getLifecycle) {
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissionUtils.permissionGranted(requestCode, REQUEST_CODE_LOCATION_PERMISSIONS, grantResults)) {
            Toast.makeText(requireContext(), "Permissions granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "App may not work properly", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
//        if (issignin()) {
//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, MapFragment()).commit()
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
