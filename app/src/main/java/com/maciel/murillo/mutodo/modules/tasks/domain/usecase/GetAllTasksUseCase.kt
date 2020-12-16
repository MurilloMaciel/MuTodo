package com.maciel.murillo.mutodo.modules.tasks.domain.usecase

import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository

class GetAllTasksUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(): List<Task> {
        return repository.findAll()
    }
}