package com.maciel.murillo.mutodo.di.task

import com.maciel.murillo.mutodo.core.platform.AlarmManager
import com.maciel.murillo.mutodo.modules.tasks.data.datasource.AlarmLocalDatasource
import com.maciel.murillo.mutodo.modules.tasks.data.datasource.TaskLocalDatasource
import com.maciel.murillo.mutodo.modules.tasks.data.local.AlarmLocalDatasourceImpl
import com.maciel.murillo.mutodo.modules.tasks.data.local.TaskLocalDatasourceImpl
import com.maciel.murillo.mutodo.modules.tasks.data.repository.AlarmRepositoryImpl
import com.maciel.murillo.mutodo.modules.tasks.data.repository.TaskRepositoryImpl
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.AlarmRepository
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val taskDataModule = module {
    single { AlarmManager(androidContext()) }
    single<TaskLocalDatasource> { TaskLocalDatasourceImpl(androidContext()) }
    single<TaskRepository> { TaskRepositoryImpl(get()) }
    single<AlarmLocalDatasource> { AlarmLocalDatasourceImpl(get()) }
    single<AlarmRepository> { AlarmRepositoryImpl(get()) }
}