package com.maciel.murillo.mutodo.di

import com.maciel.murillo.mutodo.di.task.taskDataModule
import com.maciel.murillo.mutodo.di.task.taskDomainModule
import com.maciel.murillo.mutodo.di.task.taskPresentationModule
import org.koin.core.module.Module

val appComponent: List<Module> = listOf(
        taskDataModule,
        taskDomainModule,
        taskPresentationModule
)