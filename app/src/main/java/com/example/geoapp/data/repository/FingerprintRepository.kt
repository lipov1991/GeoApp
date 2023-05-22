package com.example.geoapp.data.repository


import android.util.Log
import com.example.geoapp.data.repository.db.signal.Signal
import com.example.geoapp.data.repository.db.signal.SignalDatabase


class FingerprintRepository(
    private val signalDatabase: SignalDatabase
) {

    companion object {
        private const val TAG = "pw.FingRepo"
    }
    suspend fun saveSignal(signal: Signal) {
        try {
            signalDatabase.signalDao().save(signal)
        } catch (exception: Exception) {
            Log.d(TAG, "cannot save signal")
        }
    }

    suspend fun deleteSignal(signal: Signal) {
        try {
            signalDatabase.signalDao().delete(signal)
        } catch (exception: Exception) {
            Log.d(TAG, "cannot delete signal")
        }
    }

    suspend fun getAllSignal(): List<Signal>? {
        return try {
            signalDatabase.signalDao().getAll()
        } catch (exception: Exception) {
            Log.d(TAG, " cannot get signals")
            null
        }
    }
}
