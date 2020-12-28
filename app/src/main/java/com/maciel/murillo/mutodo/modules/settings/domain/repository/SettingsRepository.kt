package com.maciel.murillo.mutodo.modules.settings.domain.repository

interface SettingsRepository {

    suspend fun getUserName(): String?

    suspend fun setUserName(userName: String)

    suspend fun getAlarmVibrate(): Boolean

    suspend fun setAlarmVibrate(vibrate: Boolean)

    suspend fun isFirstTimeLaunchingApp(): Boolean

    suspend fun setFirstTimeLaunchingApp(firstTime: Boolean)

    suspend fun setDefaultAlarmTone()
}