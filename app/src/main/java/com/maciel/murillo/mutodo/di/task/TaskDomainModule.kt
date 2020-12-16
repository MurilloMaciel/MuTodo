package com.maciel.murillo.mutodo.di.task

import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.*
import org.koin.dsl.module

val taskDomainModule = module {
    factory { DeleteTaskUseCase(get()) }
    factory { GetAllTasksUseCase(get()) }
    factory { GetTaskByIdUseCase(get()) }
    factory { InsertTaskUseCase(get()) }
    factory { InsertTaskListUseCase(get()) }
}