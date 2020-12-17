package com.maciel.murillo.mutodo.modules.settings.domain.repository

import android.net.Uri

interface SettingsRepository {

    suspend fun getAlarmSound(): Uri?

    suspend fun setAlarmSound(toneUri: Uri)

    suspend fun getAlarmVibrate(): Boolean

    suspend fun setAlarmVibrate(vibrate: Boolean)

    suspend fun isFirstTimeLaunchingApp(): Boolean

    suspend fun setFirstTimeLaunchingApp(firstTime: Boolean)

    suspend fun setDefaultAlarmTone()
}