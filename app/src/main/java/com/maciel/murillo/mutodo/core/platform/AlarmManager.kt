package com.maciel.murillo.mutodo.core.platform

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.maciel.murillo.mutodo.core.platform.receivers.AlarmReceiver
import com.maciel.murillo.mutodo.core.platform.receivers.ScheduleAlarmsReceiver
import java.util.*

class AlarmManager(private val context: Context) {

    private val manager by lazy { (context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager) }

    fun scheduleAlarm(dateTime: Calendar, taskId: Long) {
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(AlarmReceiver.EXTRA_TASK_ID, taskId)

        val pending = PendingIntent.getBroadcast(context, taskId.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            manager?.setExact(AlarmManager.RTC_WAKEUP, dateTime.timeInMillis, pending)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            manager?.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, dateTime.timeInMillis, pending)
        } else {
            manager?.setAlarmClock(AlarmManager.AlarmClockInfo(dateTime.timeInMillis, null), pending)
        }
    }

    fun cancelAlarm(taskId: Long) {
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(AlarmReceiver.EXTRA_TASK_ID, taskId)

        val alarmIntent = PendingIntent.getBroadcast(context, taskId.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmIntent.cancel()

        manager?.cancel(alarmIntent)
    }

    fun scheduleAlarmUpdates(calendar: Calendar) {
        val intent = Intent(context, ScheduleAlarmsReceiver::class.java)
        val alarmIntent = PendingIntent.getBroadcast(context, 999999999, intent,  PendingIntent.FLAG_UPDATE_CURRENT)
        manager?.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, alarmIntent)
    }
}