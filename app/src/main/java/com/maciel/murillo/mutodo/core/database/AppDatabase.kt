package com.maciel.murillo.mutodo.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maciel.murillo.mutodo.modules.tasks.data.local.TaskDao
import com.maciel.murillo.mutodo.modules.tasks.data.model.AlarmData
import com.maciel.murillo.mutodo.modules.tasks.data.model.TaskData

@Database(entities = [
    TaskData::class
], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private const val DATABASE_NAME = "MuTodo_Tasks_Database"

        @Volatile private lateinit var INSTANCE: AppDatabase

        fun getInstance(context: Context): AppDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }
    }
}