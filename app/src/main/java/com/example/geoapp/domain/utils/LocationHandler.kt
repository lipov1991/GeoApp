package com.example.geoapp.domain.utils

import kotlin.math.abs
import kotlin.math.min


data class Router(
    val name: String,
    val signal_strength: Double
) {}

data class Room(
    val name: String,
    val routers: List<Router>
) {}

data class Fingerprint(
    val bssid: String,
    val signal_level: Double,
    val room_name: String,
    val direction: Int
) {}

data class PointFingerprints(val bssid: String, val signal_level: Double, val direction: Int = 0) {}

data class AverageSignalStrength(val bssid:String, val average_signal_level: Double) {}

data class TempMin(var room_name: String, var min: Double) {}

class LocationHandler(
    val historicFingerprints: List<Fingerprint>,
    val pointsFingerprints: List<PointFingerprints>
) {

    //TODO grupowanie pomiarów
    //Co kazde klikniecie dostajemy pomiary do wszystkich routerów z danego kierunku, np. north
    //Znalezc pomiary z 4 kierunków dla każdego routera
    //Wywołać funkcje uśredniania (get_average_measurement_from_directions) dla każdego routera
    //Utworzyć AverageSignalStrength(nazwa pokoju, średnia wartość do routera z tego pokoju)
    //Wyliczyć dystans funkcją (calculate_distance) - zmienić na euclidean distance
    //Zwracamy nazwę pokoju

    fun get_average_measurement_from_directions(measurements: List<PointFingerprints>): Double {
        """
            przekazujemy juz pogrupowane fingepritny - np 4 z tym samym pokojem i tym samym routerem
        """.trimIndent()
        var average_value: Double = 0.0;
        for (measurement in measurements) {
            average_value += measurement.signal_level;
        }
        return average_value / measurements.size;
    }

    fun calculate_distance(average_measurement: AverageSignalStrength, Rooms: List<Room>): String {
        var min = TempMin("0", -1.0)
        for(room in Rooms){
            for(router in room.routers){
                if(router.name == average_measurement.bssid){
                    var temp = abs(average_measurement.average_signal_level - router.signal_strength)
                    if(temp < min.min){
                        min.room_name = room.name;
                        min.min = temp;
                    }
                }
            }
        }
        return min.room_name;
    }
}