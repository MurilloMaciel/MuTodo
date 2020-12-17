package com.maciel.murillo.mutodo.di.settings

import com.maciel.murillo.mutodo.modules.settings.data.datasource.SettingsLocalDatasource
import com.maciel.murillo.mutodo.modules.settings.data.local.SettingsLocalDatasourceImpl
import com.maciel.murillo.mutodo.modules.settings.data.repository.SettingsRepositoryImpl
import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val settingsDataModule = module {
    single<SettingsLocalDatasource> { SettingsLocalDatasourceImpl(androidContext()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}