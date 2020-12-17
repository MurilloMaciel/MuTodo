package com.maciel.murillo.mutodo.modules.settings.data.repository

import android.net.Uri
import com.maciel.murillo.mutodo.modules.settings.data.datasource.SettingsLocalDatasource
import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository

class SettingsRepositoryImpl(private val localDataSource: SettingsLocalDatasource) : SettingsRepository {

    override suspend fun setAlarmSound(toneUri: Uri) = localDataSource.setAlarmSound(toneUri)

    override suspend fun getAlarmSound() = localDataSource.getAlarmSound()

    override suspend fun setAlarmVibrate(vibrate: Boolean) = localDataSource.setAlarmVibrate(vibrate)

    override suspend fun getAlarmVibrate() = localDataSource.getAlarmVibrate()

    override suspend fun setFirstTimeLaunchingApp(firstTime: Boolean) = localDataSource.setFirstTimeLaunchingApp(firstTime)

    override suspend fun isFirstTimeLaunchingApp() = localDataSource.isFirstTimeLaunchingApp()

    override suspend fun setDefaultAlarmTone()  = localDataSource.setDefaultAlarmTone()
}