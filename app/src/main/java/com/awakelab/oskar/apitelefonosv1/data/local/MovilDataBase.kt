package com.awakelab.oskar.apitelefonosv1.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovilEntity::class, DetalleEntity::class], version = 1)
abstract class MovilDataBase() : RoomDatabase() {
    abstract fun getMovilDao(): MovilDao

    companion object {
        @Volatile
        private var INSTANCE: MovilDataBase? = null

        fun getDatabase(context: Context): MovilDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, MovilDataBase::class.java, "movil_ddbb"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}