package com.maciel.murillo.mutodo.core.platform.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.ScheduleAllAlarmsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class BootReceiver : BroadcastReceiver(), KoinComponent {

    private val scheduleAllAlarmsUseCase: ScheduleAllAlarmsUseCase by inject()

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            onBootCompleted()
        }
    }

    private fun onBootCompleted() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                scheduleAllAlarmsUseCase.invoke()
            } catch (e: Exception) {
                Log.e("BootReceiver", "error -> $e")
            }
        }
    }
}