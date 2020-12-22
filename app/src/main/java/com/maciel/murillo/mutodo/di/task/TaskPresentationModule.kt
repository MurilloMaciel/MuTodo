package com.maciel.murillo.mutodo.di.task

import com.maciel.murillo.mutodo.modules.tasks.presentation.TasksViewModel
import com.maciel.murillo.mutodo.modules.tasks.presentation.addtask.AddTaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val taskPresentationModule = module {

    viewModel { TasksViewModel(get(), get(), get()) }
    viewModel { AddTaskViewModel(get(), get(), get()) }
}