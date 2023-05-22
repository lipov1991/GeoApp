package com.example.geoapp.data.repository.db.signal

import androidx.room.*

@Dao
interface SignalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(signal: Signal)

    @Delete
    suspend fun delete(signal: Signal)

    @Query("SELECT * FROM signal")
    suspend fun getAll(): List<Signal>


}

