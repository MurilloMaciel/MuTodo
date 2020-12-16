package com.maciel.murillo.mutodo.modules.tasks.data.local

import android.content.Context
import androidx.room.RoomDatabase
import com.maciel.murillo.mutodo.core.database.AppDatabase
import com.maciel.murillo.mutodo.modules.tasks.data.datasource.TaskLocalDatasource
import com.maciel.murillo.mutodo.modules.tasks.data.model.TaskData

class TaskLocalDatasourceImpl(private val context: Context) : TaskLocalDatasource {

//    private val db: RoomDatabase = AppDatabase.getInstance(context)

    override suspend fun insert(task: TaskData) = AppDatabase.getInstance(context).taskDao().insert(task)

    override suspend fun insertAll(tasks: List<TaskData>) = AppDatabase.getInstance(context).taskDao().insertAll(tasks)

    override suspend fun update(task: TaskData) = AppDatabase.getInstance(context).taskDao().update(task)

    override suspend fun delete(id: Long) = AppDatabase.getInstance(context).taskDao().delete(id)

    override suspend fun findAll(): List<TaskData> = AppDatabase.getInstance(context).taskDao().findAll()

    override suspend fun findById(id: Long): TaskData = AppDatabase.getInstance(context).taskDao().findById(id)
}