package com.maciel.murillo.mutodo.core.platform.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.ScheduleAllAlarmsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class ScheduleAlarmsReceiver : BroadcastReceiver(), KoinComponent {

    private val scheduleAllAlarmsUseCase: ScheduleAllAlarmsUseCase by inject()

    override fun onReceive(context: Context, intent: Intent) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                scheduleAllAlarmsUseCase.invoke()
            } catch (e: Exception) {
                // TODO: 16/12/2020 generate error
            }
        }
    }
}