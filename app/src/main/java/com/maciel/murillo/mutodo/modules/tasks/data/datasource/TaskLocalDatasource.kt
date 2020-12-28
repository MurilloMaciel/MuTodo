package com.maciel.murillo.mutodo.modules.tasks.data.datasource

import com.maciel.murillo.mutodo.modules.tasks.data.model.TaskData

interface TaskLocalDatasource {

    suspend fun insert(task: TaskData): Long

    suspend fun insertAll(tasks: List<TaskData>)

    suspend fun update(task: TaskData)

    suspend fun delete(task: TaskData)

    suspend fun findAll(): List<TaskData>

    suspend fun findAllByCategory(categoryType: String): List<TaskData>

    suspend fun findById(id: Long): TaskData

    suspend fun countByCategory(categoryType: String): Int
}