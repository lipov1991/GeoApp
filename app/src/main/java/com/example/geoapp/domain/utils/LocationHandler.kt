package com.example.geoapp.domain.utils

import android.util.Log
import com.example.geoapp.domain.utils.TestData. *
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
    init {
        this.add_fingerprints_from_one_direction(pointsFingerprints)
    }

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

    fun get_average_router_per_4_directions(): List<AverageSignalStrength> {
        """
            funkcja do wyliczenia sredniej dla routerów. 
            Aby działała trzeba wczytać dane a następnie je posortować
            dane musza zawierac po 4 kierunki dla routerów
            dane musza prezentowac sie nastepujaco:
            Router(router1, 1.0)
            Router(router1, 4.0)
            Router(router1, 2.0)
            Router(router1, 5.0)
            Router(router2, 1.0)
            Router(router2, 11.0)
            Router(router2, 41.0)
            Router(router2, 14.0)
           
        """.trimIndent()
        val result = mutableListOf<AverageSignalStrength>()
        var sum = 0.0
        var count = 0

        for (i in this.fingerprints.indices) {
            sum += this.fingerprints[i].signal_strength
            count++

            if (count == 4) {
                result.add(AverageSignalStrength(this.fingerprints[i].name, sum / 4))
                sum = 0.0
                count = 0
            }
        }
        return result
    }

    fun create_AverageSignalStrength(routers: List<Router>) {
//        var rooms: List<Room> = TestData().get_testRooms();
//        var averageSignalStrengthList: MutableList<AverageSignalStrength> = mutableListOf();
//        for (router in routers) {
//            for (room in rooms) {
//                for (historic_router in room.routers) {
//                    if (historic_router.name == router.name) {
//                        val tmp_averageSignal = AverageSignalStrength(room.name, router.name, router.signal_strength)
//                        averageSignalStrengthList.add(tmp_averageSignal);
//                    }
//                }
//            }
//        }
//        return averageSignalStrengthList.toList();
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

    fun calculate_distance(average_measurements: List<AverageSignalStrength>, Rooms: List<Room>): String {
        """
            bierze to pod uwage jeden router - powinno brac wszystkie i pozniej znajdowac przy nich minimalna wartosc
        """.trimIndent()
        var min = TempMin("0", 1000000.0)
        for(average_measurement in average_measurements) {
            for(room in Rooms){
                for(router in room.routers){
                    if(router.name == average_measurement.bssid){
                        var temp = abs(average_measurement.average_signal_level - router.signal_strength)
                        if(temp < min.min){
                            min.room_name = room.name;
                            min.min = temp;
                            Log.d("TestData", String.format("room_name: %s, min %s", min.room_name, min.min));
                        }
                    }
                }
            }
        }

        return min.room_name;
    }
}