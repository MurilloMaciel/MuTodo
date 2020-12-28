package com.maciel.murillo.mutodo.modules.settings.data.datasource

interface SettingsLocalDatasource {

    fun getUserName(): String?

    fun setUserName(userName: String)

    fun getAlarmVibrate(): Boolean

    fun setAlarmVibrate(vibrate: Boolean)

    fun setFirstTimeLaunchingApp(firstTimeLaunching: Boolean)

    fun isFirstTimeLaunchingApp(): Boolean

    fun setDefaultAlarmTone()
}