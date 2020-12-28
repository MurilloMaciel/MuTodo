package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task

import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository

class CountByCategoryUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(categoryType: CategoryType): Int = repository.countByCategory(categoryType)
}