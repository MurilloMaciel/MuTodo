package com.maciel.murillo.mutodo.modules.tasks.data.local

import com.maciel.murillo.mutodo.modules.tasks.data.datasource.TaskLocalDatasource
import com.maciel.murillo.mutodo.modules.tasks.data.model.TaskData

class TaskLocalDatasourceImpl(private val taskDao: TaskDao) : TaskLocalDatasource {

    override suspend fun insert(task: TaskData) = taskDao.insert(task)

    override suspend fun insertAll(tasks: List<TaskData>) = taskDao.insertAll(tasks)

    override suspend fun update(task: TaskData) = taskDao.update(task)

    override suspend fun delete(id: Long) = taskDao.delete(id)

    override suspend fun findAll(): List<TaskData> = taskDao.findAll()

    override suspend fun findById(id: Long): TaskData = taskDao.findById(id)
}