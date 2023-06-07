package com.example.geoapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geoapp.R
import com.example.geoapp.ui.auth.AuthFragment
import com.example.geoapp.ui.map.MapFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, MapFragment()).commit()
    }
}