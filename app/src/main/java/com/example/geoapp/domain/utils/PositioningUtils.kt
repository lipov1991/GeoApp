package com.example.geoapp.domain.utils

//data class Fingerprint(
//    val bssid: String,
//    val signal_level: Double,
//    val room_name: String,
//    val direction: Int
//) {}
//
//data class PointFingerprints(val bssid: String, val signal_level: Double, val direction: Int = 0) {}
//
//class LocationHandler(
//    val historicFingerprints: List<Fingerprint>,
////    val pointsFingerprints: List<PointFingerprints>
//) {
//    var grouped_data = hashMapOf<String, Any?>();
//
//    fun group_measurements() {
//        for (measurement in historicFingerprints) {
//            if (!grouped_data.containsKey(measurement.room_name)) {
//                grouped_data.put(measurement.room_name, hashMapOf<String, Any?>())
//            }
//
//            if (!grouped_data[measurement.room_name].containsKey(measurement.bssid)) {
//                grouped_data[measurement.room_name].put(measurement.bssid, mutableListOf<Fingerprint>())
//            }
//
//            grouped_data.get(measurement.room_name).get(measurement.bssid).add(measurement)
//        }
//    }
//
//    fun get_average_measurement_from_directions(measurements: List<PointFingerprints>): Double {
//        """
//            przekazujemy juz pogrupowane fingepritny - np 4 z tym samym pokojem i tym samym routerem
//        """.trimIndent()
//        var average_value: Double = 0.0;
//        for (measurement in measurements) {
//            average_value += measurement.signal_level;
//        }
//        return average_value / measurements.size;
//    }
//}

// TODO Tutaj będzie logika związana z pozycjonowaniem użytkownika w budynku
class PositioningUtils {

    fun locateUser() {
        // TODO
    }

    fun calcPosition(
        historicFingerprints: List<Fingerprint>,
        pointsFingerprints: List<PointFingerprints>
    ) {
        for (fingerprint in historicFingerprints) {
            print(fingerprint)
        }
    }
}
