package com.maciel.murillo.mutodo.di.settings

import com.maciel.murillo.mutodo.modules.settings.presentation.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsPresentationModule = module {

    viewModel { SettingsViewModel(get(), get(), get(), get()) }

}