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
            Router("Router_1_1", signal_strength = 1.0),
            Router("Router_1_2", signal_strength = 1.0),
            Router("Router_1_3", signal_strength = 1.0),
        )),
        Room("room_2", listOf(
            Router("Router_2_1", signal_strength = 1.0),
            Router("Router_2_2", signal_strength = 1.0),
            Router("Router_2_3", signal_strength = 1.0),
        )),
        Room("room_3", listOf(
            Router("Router_3_1", signal_strength = 1.0),
            Router("Router_3_2", signal_strength = 1.0),
            Router("Router_3_3", signal_strength = 1.0),
        ))
    )

    private val testPointFingerprint0: List<PointFingerprints> = listOf(
        PointFingerprints("Router_1_1", 1.0, 0),
        PointFingerprints("Router_1_2", 1.0, 0),
        PointFingerprints("Router_1_3", 1.0, 0),
        PointFingerprints("Router_2_1", 1.0, 0),
        PointFingerprints("Router_2_2", 1.0, 0),
        PointFingerprints("Router_2_3", 1.0, 0),
        PointFingerprints("Router_3_1", 1.0, 0),
        PointFingerprints("Router_3_2", 1.0, 0),
        PointFingerprints("Router_3_3", 1.0, 0),
    )

    private val testPointFingerprint1: List<PointFingerprints> = listOf(
        PointFingerprints("Router_1_1", 2.0, 1),
        PointFingerprints("Router_1_2", 2.0, 1),
        PointFingerprints("Router_1_3", 2.0, 1),
        PointFingerprints("Router_2_1", 2.0, 1),
        PointFingerprints("Router_2_2", 2.0, 1),
        PointFingerprints("Router_2_3", 2.0, 1),
        PointFingerprints("Router_3_1", 2.0, 1),
        PointFingerprints("Router_3_2", 2.0, 1),
        PointFingerprints("Router_3_3", 2.0, 1),
    )

    private val testPointFingerprint2: List<PointFingerprints> = listOf(
        PointFingerprints("Router_1_1", 3.0, 2),
        PointFingerprints("Router_1_2", 3.0, 2),
        PointFingerprints("Router_1_3", 3.0, 2),
        PointFingerprints("Router_2_1", 3.0, 2),
        PointFingerprints("Router_2_2", 3.0, 2),
        PointFingerprints("Router_2_3", 3.0, 2),
        PointFingerprints("Router_3_1", 3.0, 2),
        PointFingerprints("Router_3_2", 3.0, 2),
        PointFingerprints("Router_3_3", 3.0, 2),
    )

    private val testPointFingerprint3: List<PointFingerprints> = listOf(
        PointFingerprints("Router_1_1", 4.0, 3),
        PointFingerprints("Router_1_2", 4.0, 3),
        PointFingerprints("Router_1_3", 4.0, 3),
        PointFingerprints("Router_2_1", 4.0, 3),
        PointFingerprints("Router_2_2", 4.0, 3),
        PointFingerprints("Router_2_3", 4.0, 3),
        PointFingerprints("Router_3_1", 4.0, 3),
        PointFingerprints("Router_3_2", 4.0, 3),
        PointFingerprints("Router_3_3", 4.0, 3),
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