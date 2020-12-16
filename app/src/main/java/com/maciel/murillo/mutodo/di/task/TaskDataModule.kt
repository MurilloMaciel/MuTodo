package com.maciel.murillo.mutodo.di.task

import com.maciel.murillo.mutodo.modules.tasks.data.datasource.TaskLocalDatasource
import com.maciel.murillo.mutodo.modules.tasks.data.local.TaskLocalDatasourceImpl
import com.maciel.murillo.mutodo.modules.tasks.data.repository.TaskRepositoryImpl
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val taskDataModule = module {
    single<TaskLocalDatasource> { TaskLocalDatasourceImpl(androidContext()) }
    single<TaskRepository> { TaskRepositoryImpl(get()) }
}