package com.example.geoapp.data.repository.fingerprint

import androidx.room.*
import com.example.geoapp.data.repository.fingerprint.SignalEntity

@Dao
interface SignalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(signal: SignalEntity)

    @Delete
    suspend fun delete(signal: SignalEntity)

    @Query("SELECT * FROM signal")
    suspend fun getAll(): List<SignalEntity>
}
