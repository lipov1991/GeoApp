package com.example.geoapp.data.repository.fingerprint

import android.util.Log


class FingerprintRepository(
    private val signalDatabase: SignalDatabase
) {

    companion object {
        private const val TAG = "pw.FingRepo"
    }

    suspend fun saveSignal(signal: SignalEntity) {
        try {
            signalDatabase.signalDao().save(signal)
        } catch (exception: Exception) {
            Log.d(TAG, "cannot save signal")
        }
    }

    suspend fun deleteSignal(signal: SignalEntity) {
        try {
            signalDatabase.signalDao().delete(signal)
        } catch (exception: Exception) {
            Log.d(TAG, "cannot delete signal")
        }
    }

    suspend fun getAllSignal(): List<SignalEntity>? {
        return try {
            signalDatabase.signalDao().getAll()
        } catch (exception: Exception) {
            Log.d(TAG, " cannot get signals")
            null
        }
    }
}
