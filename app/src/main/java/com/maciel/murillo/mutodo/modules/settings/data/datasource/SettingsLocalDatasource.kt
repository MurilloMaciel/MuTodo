package com.maciel.murillo.mutodo.modules.settings.data.datasource

import android.net.Uri

interface SettingsLocalDatasource {

    fun getAlarmSound(): Uri?

    fun setAlarmSound(alarmTone: Uri)

    fun getAlarmVibrate(): Boolean

    fun setAlarmVibrate(vibrate: Boolean)

    fun setFirstTimeLaunchingApp(firstTimeLaunching: Boolean)

    fun isFirstTimeLaunchingApp(): Boolean

    fun setDefaultAlarmTone()
}