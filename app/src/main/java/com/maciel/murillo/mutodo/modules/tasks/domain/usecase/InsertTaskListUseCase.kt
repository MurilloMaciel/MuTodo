package com.maciel.murillo.mutodo.modules.tasks.domain.usecase

import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository

class InsertTaskListUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(tasks: List<Task>) {
        repository.insertAll(tasks)

        // TODO: 15/12/2020 schedule alarm into alarm manager
        tasks.forEach { it.alarm?.run {  } }
    }
}