package com.maciel.murillo.mutodo.core.platform

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.maciel.murillo.mutodo.core.extensions.log
import com.maciel.murillo.mutodo.core.extensions.safe
import com.maciel.murillo.mutodo.core.extensions.toDateTimeCalendar
import com.maciel.murillo.mutodo.core.platform.receivers.AlarmReceiver
import com.maciel.murillo.mutodo.core.platform.receivers.ScheduleAlarmsReceiver
import com.maciel.murillo.mutodo.modules.tasks.data.model.TaskData
import com.maciel.murillo.mutodo.modules.tasks.domain.model.RepeatType
import java.util.*

class AlarmManager(private val context: Context) {

    private val manager by lazy { (context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager) }

    fun scheduleAlarm(task: TaskData) {
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(AlarmReceiver.EXTRA_TASK_ID, task.id.safe().toInt())

        val pending = PendingIntent.getBroadcast(context, task.id.safe().toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val triggerAtMillis = task.alarm?.nextAlarmDate?.toDateTimeCalendar()?.timeInMillis.safe()

        if (task.alarm?.repeatType != RepeatType.NOT_REPEAT.ordinal) {
            manager?.setInexactRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, AlarmManager.INTERVAL_DAY, pending)
        } else {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                manager?.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pending)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                manager?.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, pending)
            } else {
                manager?.setAlarmClock(AlarmManager.AlarmClockInfo(triggerAtMillis, null), pending)
            }
        }
    }

    fun cancelAlarm(task: TaskData) {
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(AlarmReceiver.EXTRA_TASK_ID, task.id.safe().toInt())

        val alarmIntent = PendingIntent.getBroadcast(context, task.id.safe().toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmIntent.cancel()

        manager?.cancel(alarmIntent)
    }

    fun updateAlarm(task: TaskData) {
        cancelAlarm(task)
        scheduleAlarm(task)
    }

    fun scheduleAlarmUpdates(calendar: Calendar) {
        val intent = Intent(context, ScheduleAlarmsReceiver::class.java)
        val alarmIntent = PendingIntent.getBroadcast(context, 999999999, intent,  PendingIntent.FLAG_UPDATE_CURRENT)
        manager?.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, alarmIntent)
    }
}