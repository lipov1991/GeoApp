package com.example.geoapp.domain.utils

class TestData {
    val testFingerprint: List<Fingerprint> = listOf(
        Fingerprint("Router_1_1", 1.0, "room_1", 0),
        Fingerprint("Router_1_2", 1.0, "room_1", 0),
        Fingerprint("Router_1_3", 1.0, "room_1", 0),
        Fingerprint("Router_2_1", 1.0, "room_2", 0),
        Fingerprint("Router_2_2", 1.0, "room_2", 0),
        Fingerprint("Router_2_3", 1.0, "room_2", 0),
        Fingerprint("Router_3_1", 1.0, "room_3", 0),
        Fingerprint("Router_3_2", 1.0, "room_3", 0),
        Fingerprint("Router_3_3", 1.0, "room_3", 0)
    )

    private val testRooms: List<Room> = listOf(
        Room("room_1", listOf(
            Router("Router_1_1", 95.0),
            Router("Router_1_2", 65.0),
            Router("Router_1_3", 80.0),
        )),
        Room("room_2", listOf(
            Router("Router_2_1", 60.0),
            Router("Router_2_2", 64.0),
            Router("Router_2_3", 84.0),
        )),
        Room("room_3", listOf(
            Router("Router_3_1", 70.0),
            Router("Router_3_2", 80.0),
            Router("Router_3_3", 73.2),
        ))
    )

    private val testPointFingerprint0: List<PointFingerprints> = listOf(
        PointFingerprints("Router_1_1", 17.0, 0),
        PointFingerprints("Router_1_2", 14.0, 0),
        PointFingerprints("Router_1_3", 9.00, 0),
        PointFingerprints("Router_2_1", 34.0, 0),
        PointFingerprints("Router_2_2", 24.0,0),
        PointFingerprints("Router_2_3", 20.0, 0),
        PointFingerprints("Router_3_1", 8.00, 0),
        PointFingerprints("Router_3_2", 12.0, 0),
        PointFingerprints("Router_3_3", 9.00, 0),
    )

    private val testPointFingerprint1: List<PointFingerprints> = listOf(
        PointFingerprints("Router_1_1", 17.4, 1),
        PointFingerprints("Router_1_2", 14.2, 1),
        PointFingerprints("Router_1_3", 9.67, 1),
        PointFingerprints("Router_2_1", 34.5, 1),
        PointFingerprints("Router_2_2", 23.4, 1),
        PointFingerprints("Router_2_3", 23.4, 1),
        PointFingerprints("Router_3_1", 9.30, 1),
        PointFingerprints("Router_3_2", 14.2, 1),
        PointFingerprints("Router_3_3", 8.30, 1),
    )

    private val testPointFingerprint2: List<PointFingerprints> = listOf(
        PointFingerprints("Router_1_1", 17.5, 2),
        PointFingerprints("Router_1_2", 14.5, 2),
        PointFingerprints("Router_1_3", 9.50, 2),
        PointFingerprints("Router_2_1", 34.5, 2),
        PointFingerprints("Router_2_2", 24.5, 2),
        PointFingerprints("Router_2_3", 20.5, 2),
        PointFingerprints("Router_3_1", 8.50, 2),
        PointFingerprints("Router_3_2", 12.5, 2),
        PointFingerprints("Router_3_3", 9.50, 2),
    )

    private val testPointFingerprint3: List<PointFingerprints> = listOf(
        PointFingerprints("Router_1_1", 18.0, 3),
        PointFingerprints("Router_1_2", 15.0, 3),
        PointFingerprints("Router_1_3", 10.0, 3),
        PointFingerprints("Router_2_1", 35.0, 3),
        PointFingerprints("Router_2_2", 25.0, 3),
        PointFingerprints("Router_2_3", 21.0, 3),
        PointFingerprints("Router_3_1", 9.00, 3),
        PointFingerprints("Router_3_2", 13.0, 3),
        PointFingerprints("Router_3_3", 10.0, 3),
    )
    fun get_PointFingerprints(): List<PointFingerprints> {
//        return listOf(testPointFingerprint0, testPointFingerprint1, testPointFingerprint2, testPointFingerprint3)
        return listOf(testPointFingerprint0, testPointFingerprint1, testPointFingerprint2, testPointFingerprint3).flatten()
    }

    fun get_Fingerprints(): List<Fingerprint> {
        return testFingerprint;
    }

    fun get_testRooms(): List<Room> {
        return testRooms;
    }

}