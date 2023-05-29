package com.example.geoapp.data.repository.fingerprint


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "signal")
data class SignalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val ssid: String,
    val strength: Int
)
