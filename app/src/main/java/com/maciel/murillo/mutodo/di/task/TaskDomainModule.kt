package com.maciel.murillo.mutodo.di.task

import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.*
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.*
import org.koin.dsl.module

val taskDomainModule = module {
    factory { DeleteTaskUseCase(get(), get()) }
    factory { GetAllTasksUseCase(get()) }
    factory { GetTaskByIdUseCase(get()) }
    factory { InsertTaskUseCase(get(), get()) }
    factory { InsertTaskListUseCase(get(), get()) }
    factory { UpdateTaskUseCase(get(), get(), get()) }
    factory { CancelAlarmScheduleUseCase(get()) }
    factory { GetNextAlarmUseCase() }
    factory { ScheduleAlarmUpdateUseCase(get()) }
    factory { ScheduleAlarmUseCase(get()) }
    factory { ScheduleAllAlarmsUseCase(get(), get(), get()) }
}