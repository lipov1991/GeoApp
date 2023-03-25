package com.example.geoapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geoapp.R
import com.example.geoapp.databinding.ActivityAuthBinding
import com.example.geoapp.ui.auth.AuthFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_auth)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, AuthFragment())
            .commit()
    }
}
