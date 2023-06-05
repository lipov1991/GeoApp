package com.example.geoapp.data.repository

import android.util.Log

class buildingrepository {

    private val floorLevels = listOf(
        Floor("Piętro 1", true, "1"),
        Floor("Piętro 2", false, "2"),
        Floor("Piętro 3", false, "3"),
        Floor("Piętro 4", false, "4")

    )
    public fun getFloors(): List<Floor>{
//        for (subList in floorLevels ) {
//            Log.d("ID", subList.id)
//        }
        return floorLevels
    }

}