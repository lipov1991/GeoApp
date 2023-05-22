package com.example.geoapp.data.repository.db.signal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Signal::class], version = 1)
abstract class SignalDatabase : RoomDatabase() {
    abstract fun signalDao(): SignalDao

    companion object {
        @Volatile
        private var INSTANCE: SignalDatabase? = null

        fun getDatabase(context: Context): SignalDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SignalDatabase::class.java,
                    "signal_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}
