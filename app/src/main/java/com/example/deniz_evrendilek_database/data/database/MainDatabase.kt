package com.example.deniz_evrendilek_database.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.deniz_evrendilek_database.data.dao.ExerciseEntryDao
import com.example.deniz_evrendilek_database.data.model.ExerciseEntry

@Database(entities = [ExerciseEntry::class], version = 1)
abstract class MainDatabase : RoomDatabase() {
    abstract val exerciseEntryDao: ExerciseEntryDao

    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, MainDatabase::class.java, "main_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}