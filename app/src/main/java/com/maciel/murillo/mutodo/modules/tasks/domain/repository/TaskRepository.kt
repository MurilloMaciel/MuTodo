package com.maciel.murillo.mutodo.modules.tasks.domain.repository

import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task

interface TaskRepository {

    suspend fun insert(task: Task)

    suspend fun insertAll(tasks: List<Task>)

    suspend fun update(task: Task)

    suspend fun delete(id: Long)

    suspend fun findAll(): List<Task>

    suspend fun findAllByCategory(categoryType: CategoryType): List<Task>

    suspend fun findById(id: Long): Task
}