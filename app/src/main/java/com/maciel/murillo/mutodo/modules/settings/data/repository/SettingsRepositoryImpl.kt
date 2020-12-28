package com.maciel.murillo.mutodo.modules.settings.data.repository

import com.maciel.murillo.mutodo.modules.settings.data.datasource.SettingsLocalDatasource
import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository

class SettingsRepositoryImpl(private val localDataSource: SettingsLocalDatasource) : SettingsRepository {

    override suspend fun setUserName(userName: String) = localDataSource.setUserName(userName)

    override suspend fun getUserName() = localDataSource.getUserName()

    override suspend fun setAlarmVibrate(vibrate: Boolean) = localDataSource.setAlarmVibrate(vibrate)

    override suspend fun getAlarmVibrate() = localDataSource.getAlarmVibrate()

    override suspend fun setFirstTimeLaunchingApp(firstTime: Boolean) = localDataSource.setFirstTimeLaunchingApp(firstTime)

    override suspend fun isFirstTimeLaunchingApp() = localDataSource.isFirstTimeLaunchingApp()

    override suspend fun setDefaultAlarmTone()  = localDataSource.setDefaultAlarmTone()
}