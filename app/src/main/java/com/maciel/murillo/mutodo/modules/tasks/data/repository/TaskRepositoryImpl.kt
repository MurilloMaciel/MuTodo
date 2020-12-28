package com.maciel.murillo.mutodo.modules.tasks.data.repository

import com.maciel.murillo.mutodo.modules.categories.data.model.toEnumString
import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.data.datasource.TaskLocalDatasource
import com.maciel.murillo.mutodo.modules.tasks.data.model.mapToTask
import com.maciel.murillo.mutodo.modules.tasks.data.model.mapToTaskData
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository

class TaskRepositoryImpl(private val localDatasource: TaskLocalDatasource) : TaskRepository {

    override suspend fun insert(task: Task) = localDatasource.insert(task.mapToTaskData())

    override suspend fun insertAll(tasks: List<Task>) = localDatasource.insertAll(tasks.map { task -> task.mapToTaskData() })

    override suspend fun update(task: Task) = localDatasource.update(task.mapToTaskData())

    override suspend fun delete(task: Task) = localDatasource.delete(task.mapToTaskData())

    override suspend fun findAll(): List<Task> = localDatasource.findAll().map { taskData -> taskData.mapToTask() }

    override suspend fun findAllByCategory(categoryType: CategoryType): List<Task> = localDatasource.findAllByCategory(categoryType.toEnumString()).map { taskData -> taskData.mapToTask() }

    override suspend fun findById(id: Long): Task = localDatasource.findById(id).mapToTask()
}