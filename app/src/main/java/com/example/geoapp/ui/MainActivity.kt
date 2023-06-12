package com.example.geoapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geoapp.R
import com.example.geoapp.domain.utils.DialogUtils
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val dialogUtils: DialogUtils by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialogUtils.showDialog(R.string.app_name, R.string.test_message, this)
    }
}
