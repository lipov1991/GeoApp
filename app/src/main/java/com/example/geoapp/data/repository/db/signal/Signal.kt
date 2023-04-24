package com.example.geoapp.data.repository.db.signal


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Signal(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val ssid: String,
    val strength: Int,
//    pomieszczenie
//
//    val direction: Int
)

// ssid - strngth- pokój- dircetion
