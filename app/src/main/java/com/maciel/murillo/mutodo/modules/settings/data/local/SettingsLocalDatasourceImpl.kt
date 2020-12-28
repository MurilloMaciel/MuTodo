package com.maciel.murillo.mutodo.modules.settings.data.local

import android.content.Context
import android.content.SharedPreferences
import android.media.RingtoneManager
import android.net.Uri
import com.maciel.murillo.mutodo.core.extensions.safe
import com.maciel.murillo.mutodo.modules.settings.data.datasource.SettingsLocalDatasource

class SettingsLocalDatasourceImpl(context: Context) : SettingsLocalDatasource {

    companion object {
        const val PREF_KEY = "com.mutudo.PREF_KEY"
        const val FIRST_TIME_LAUNCHING_APP = "FIRST_TIME_LAUNCHING_APP"
        const val CURRENT_SOUND = "CURRENT_SOUND"
        const val ALLOW_VIBRATION = "ALLOW_VIBRATION"
        const val USER_NAME = "USER_NAME"
    }

    override fun getUserName(): String? {
        return preferences.getString(USER_NAME, "")
    }

    override fun setUserName(userName: String) {
        getEditor().putString(USER_NAME, userName).commit()
    }

    override fun getAlarmVibrate(): Boolean {
        return preferences.getBoolean(ALLOW_VIBRATION, true)
    }

    override fun setAlarmVibrate(vibrate: Boolean) {
        getEditor().putBoolean(ALLOW_VIBRATION, vibrate).commit()
    }

    override fun setFirstTimeLaunchingApp(firstTimeLaunching: Boolean) {
        getEditor().putBoolean(FIRST_TIME_LAUNCHING_APP, firstTimeLaunching).commit()
    }

    override fun isFirstTimeLaunchingApp(): Boolean {
        return preferences.getBoolean(FIRST_TIME_LAUNCHING_APP, true)
    }

    override fun setDefaultAlarmTone() {
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        getEditor().putString(CURRENT_SOUND, uri.toString()).commit()
    }

    private val preferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
    }

    private fun getEditor() = preferences.edit()
}