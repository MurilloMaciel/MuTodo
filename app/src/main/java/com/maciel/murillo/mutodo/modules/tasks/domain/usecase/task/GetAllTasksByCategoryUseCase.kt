package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task

import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository

class GetAllTasksByCategoryUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(categoryType: CategoryType): List<Task> = repository.findAllByCategory(categoryType)
}