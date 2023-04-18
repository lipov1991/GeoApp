package com.example.geoapp.domain.utils

import kotlin.math.abs


data class Router(
    val name: String,
    val signal_strength: Double
) {}

data class Room(
    val name: String,
    var routers: List<Router>? = null
) {}

data class Fingerprint(
    val bssid: String,
    val signal_level: Double,
    val room_name: String,
    val direction: Int
) {}

data class PointFingerprints(val bssid: String, val signal_level: Double, val direction: Int = 0) {}

data class TempMin(var room_name: String, var min: Double) {}

class LocationHandler(
    private val historicFingerprints: List<Fingerprint>,
    private val pointsFingerprints: List<PointFingerprints>
) {

    var fingerprints: MutableList<Router> = mutableListOf()
    var rooms: List<Room> = listOf()
    init {
        this.add_fingerprints_from_one_direction(pointsFingerprints)
        this.load_historic_fingerprints()
    }

    fun load_historic_fingerprints() {
        val rooms_tmp: MutableList<Room> = mutableListOf()

        for (historic_fingerprint in historicFingerprints) {
            if (!rooms_tmp.contains(Room(historic_fingerprint.room_name))) {
                rooms_tmp.add(Room(historic_fingerprint.room_name))
            }
        }

        for (room in rooms_tmp) {
            val routers: MutableList<Router> = mutableListOf()
            for (historic_fingerprint in historicFingerprints) {
                if (historic_fingerprint.room_name == room.name) {
                    routers.add(Router(historic_fingerprint.bssid, historic_fingerprint.signal_level))
                }
            }
            room.routers = routers
        }
        rooms = rooms_tmp.toList()
    }

    fun add_fingerprints_from_one_direction(measurements: List<PointFingerprints>) {
        for (measurement in measurements) {
            val router = Router(name = measurement.bssid, signal_strength = measurement.signal_level)
            this.fingerprints.add(router)
        }
    }

    fun sort_fingerprints() {
        this.fingerprints = this.fingerprints.sortedWith(compareBy {it.name}).toMutableList()
    }

    fun get_average_router_per_4_directions(): List<PointFingerprints> {
        val result = mutableListOf<PointFingerprints>()
        var sum = 0.0
        var count = 0

        for (i in this.fingerprints.indices) {
            sum += this.fingerprints[i].signal_strength
            count++

            if (count == 4) {
                result.add(PointFingerprints(this.fingerprints[i].name, sum / 4))
                sum = 0.0
                count = 0
            }
        }
        return result
    }

    fun calculate_distance(average_measurements: List<PointFingerprints>, Rooms: List<Room>): String {
        val min = TempMin("0", 1000000.0)
        for(average_measurement in average_measurements) {
            for(room in Rooms){
                for(router in room.routers!!){
                    if(router.name == average_measurement.bssid){
                        val temp = abs(average_measurement.signal_level - router.signal_strength)
                        if(temp < min.min){
                            min.room_name = room.name
                            min.min = temp
                        }
                    }
                }
            }
        }

        return min.room_name
    }
}