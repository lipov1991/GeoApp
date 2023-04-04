package com.example.geoapp.domain.utils

import kotlin.math.abs


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
    var fingerprints: MutableList<Router> = mutableListOf();

    //TODO grupowanie pomiarów
    //Co kazde klikniecie dostajemy pomiary do wszystkich routerów z danego kierunku, np. north
    //Znalezc pomiary z 4 kierunków dla każdego routera
    //Wywołać funkcje uśredniania (get_average_measurement_from_directions) dla każdego routera
    //Utworzyć AverageSignalStrength(nazwa pokoju, średnia wartość do routera z tego pokoju)
    //Wyliczyć dystans funkcją (calculate_distance) - zmienić na euclidean distance
    //Zwracamy nazwę pokoju

    fun add_fingerprints_from_one_direction(measurements: List<PointFingerprints>) {
        """
            dodawanie kolejnych fingerprintów do pełnej listy
            po nacisnieciu przycisku do listy maja zostac dopisane wszystkie fingerprinty z danego kierunku: north, east, south, west
            z tego powodu trzeba bedzie tą funkcję wywołać 4 razy - 4 razy nacisnąć przycisk
        """.trimIndent()
        for (measurement in measurements) {
            val router = Router(name = measurement.bssid, signal_strength = measurement.signal_level);
            this.fingerprints.add(router);
        }
    }

    fun sort_fingerprints() {
        """
            funckja sortujaca wszystkie fingerprinty routerów.
            Po jej wykonaniu fingerprinty bedą prezentowac sie nastepujaco:
            [
             Router(name='1', 'signal_strength=1),
             Router(name='1', 'signal_strength=13),
             Router(name='1', 'signal_strength=14),
             Router(name='1', 'signal_strength=12),
             
             Router(name='2', 'signal_strength=14),
             Router(name='2', 'signal_strength=17),
             Router(name='2', 'signal_strength=51),
             Router(name='2', 'signal_strength=17),
             
             Router(name='3', 'signal_strength=41),
             Router(name='3', 'signal_strength=15),
             Router(name='3', 'signal_strength=51),
             Router(name='3', 'signal_strength=14),
            ]
        """.trimIndent()
        this.fingerprints = this.fingerprints.sortedWith(compareBy {it.name}).toMutableList();
    }

    fun get_average_router_signal(routers: List<Router>): Double {
        """
            funkcja przyjmuje liste routerów - 4 (po 1 na kazdy kierunek). Następnie zwraca z nich średnią
        """.trimIndent()
        var average_value: Double = 0.0;
        for (router in routers) {
            average_value += router.signal_strength;
        }
        return average_value / routers.size;
    }

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