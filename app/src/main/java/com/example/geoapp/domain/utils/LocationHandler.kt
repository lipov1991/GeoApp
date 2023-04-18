package com.example.geoapp.domain.utils

import kotlin.math.abs


data class Router(
    val name: String,
    val signal_strength: Double,
    val room: String
)

data class Fingerprint(
    val bssid: String,
    val signal_level: Double,
    val room_name: String,
    val direction: Int
)

data class PointFingerprints(val bssid: String, val signal_level: Double, val direction: Int = 0)

data class TempMin(var room_name: String, var min: Double)

class LocationHandler(
    private val historicFingerprints: List<Fingerprint>,
    pointsFingerprints: List<PointFingerprints>
) {

    var fingerprints: MutableList<PointFingerprints> = mutableListOf()
    var routers: List<Router> = listOf()

    init {
        this.addFingerprintsFromOneDirection(pointsFingerprints)
        this.loadHistoricFingerprints()
    }

    fun loadHistoricFingerprints() {
        val routersTmp: MutableList<Router> = mutableListOf()

        for (historicFingerprint in historicFingerprints) {
            if (!routersTmp.contains(Router(historicFingerprint.bssid,
                        historicFingerprint.signal_level, historicFingerprint.room_name))) {
                routersTmp.add(Router(historicFingerprint.bssid,
                        historicFingerprint.signal_level, historicFingerprint.room_name))
            }
        }
        routers = routersTmp.toList()
    }

    fun addFingerprintsFromOneDirection(measurements: List<PointFingerprints>) {
        for (measurement in measurements) {
            this.fingerprints.add(measurement)
        }
    }

    fun sortFingerprints() {
        this.fingerprints = this.fingerprints.sortedWith(compareBy {it.bssid}).toMutableList()
    }

    fun getAverageRouterPer4Directions(): List<PointFingerprints> {
        val result = mutableListOf<PointFingerprints>()
        var sum = 0.0
        var count = 0

        for (i in this.fingerprints.indices) {
            sum += this.fingerprints[i].signal_level
            count++

            if (count == 4) {
                result.add(PointFingerprints(this.fingerprints[i].bssid, sum / 4))
                sum = 0.0
                count = 0
            }
        }
        return result
    }

    fun calculateDistance(average_measurements: List<PointFingerprints>, Routers: List<Router>): String {
        val min = TempMin("0", 1000000.0)
        for(average_measurement in average_measurements) {
            for(router in Routers){
                if(router.name == average_measurement.bssid){
                    val temp = abs(average_measurement.signal_level - router.signal_strength)
                    if(temp < min.min){
                        min.room_name = router.room
                        min.min = temp
                    }
                }
            }
        }

        return min.room_name
    }
}