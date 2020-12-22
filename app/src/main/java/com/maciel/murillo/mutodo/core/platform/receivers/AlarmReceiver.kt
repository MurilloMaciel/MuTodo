package com.maciel.murillo.mutodo.core.platform.receivers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.core.extensions.isBeforeNow
import com.maciel.murillo.mutodo.core.extensions.safe
import com.maciel.murillo.mutodo.core.helper.NotificationHelper
import com.maciel.murillo.mutodo.modules.settings.domain.usecase.GetAlarmSoundUseCase
import com.maciel.murillo.mutodo.modules.settings.domain.usecase.GetAlarmVibrateUseCase
import com.maciel.murillo.mutodo.modules.splash.presentation.SplashFragment
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.GetAllTasksUseCase
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.GetTaskByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class AlarmReceiver : BroadcastReceiver(), KoinComponent {

    companion object {
        const val EXTRA_TASK_ID = "EXTRA_TASK_ID"
        const val CHANNEL_ID_ALARM = "CHANNEL_ID_ALARM"
    }

    private val getTaskByIdUseCase: GetTaskByIdUseCase by inject()
    private val getAllTasksUseCase: GetAllTasksUseCase by inject()
    private val getAlarmSoundUseCase: GetAlarmSoundUseCase by inject()
    private val getAlarmVibrateUseCase: GetAlarmVibrateUseCase by inject()

    override fun onReceive(context: Context, intent: Intent) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val builder = buildNotification(context, intent)
                throwNotification(context, builder)
            } catch (e: Exception) {
                // TODO: 16/12/2020 generate error
            }
        }
    }

    private suspend fun throwNotification(context: Context, builder: NotificationCompat.Builder) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.makeNotificationChannel(context)
        }
        notificationManager.notify(NotificationHelper().TASK_NOTIFICATION_ID, builder.build())
    }

    private suspend fun buildNotification(context: Context, intent: Intent): NotificationCompat.Builder {
        val resultIntent = Intent(context, SplashFragment::class.java)
        val resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID_ALARM)
                .setSmallIcon(R.drawable.ic_menu_camera) // todo: ver um icone para notificação
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true)
                .apply { priority = NotificationCompat.PRIORITY_HIGH }
                .apply { color = ContextCompat.getColor(context, R.color.colorPrimary) }

        initContentTextAndTitle(context, intent, builder)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            initVibrate(builder)
            getAlarmSoundUseCase.invoke()?.run { builder.setSound(this) }
        }

        return builder
    }

    private suspend fun initContentTextAndTitle(context: Context, intent: Intent, builder: NotificationCompat.Builder) {
        try {
            val tasks = getAllTasksUseCase.invoke().filter { task -> task.alarm?.dateTime?.isBeforeNow().safe()  }

            if (tasks.size <= 1) {
                initContentFromIntent(intent, builder)
            } else {
                setTextAndTitleFromList(context, builder, tasks.size)
            }

        } catch (e: Exception) {
            builder.setContentTitle(context.getString(R.string.new_task))
            builder.setContentText(context.getString(R.string.touch_to_check))
        }
    }

    private suspend fun initContentFromIntent(intent: Intent, builder: NotificationCompat.Builder) {
        val taskId = intent.getLongExtra(EXTRA_TASK_ID, -1)

        val task = getTaskByIdUseCase.invoke(taskId)
        builder.setContentTitle(task.title)

        if (task.description?.isNotEmpty().safe()) {
            builder.setContentText(task.description)
        }
    }

    private fun setTextAndTitleFromList(context: Context, builder: NotificationCompat.Builder, tasksQuantity: Int) {
        builder.setContentTitle(tasksQuantity.toString() + " " + context.getString(R.string.notification_title_task_alarms))
        builder.setContentText(context.getString(R.string.touch_to_check))
    }

    private suspend fun initVibrate(builder: NotificationCompat.Builder) {
        if (getAlarmVibrateUseCase.invoke()) {
            builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE or NotificationCompat.DEFAULT_LIGHTS)
        } else {
            builder.setDefaults(NotificationCompat.DEFAULT_LIGHTS)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun NotificationManager.makeNotificationChannel(context: Context) {
        createNotificationChannel(
                NotificationChannel(
                        CHANNEL_ID_ALARM,
                        context.getString(R.string.notification_title_task_alarms),
                        NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                    enableLights(true)
                    enableVibration(true)
                }
        )
    }
}