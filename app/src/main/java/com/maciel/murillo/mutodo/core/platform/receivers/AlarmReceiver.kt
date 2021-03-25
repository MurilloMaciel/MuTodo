package com.maciel.murillo.mutodo.core.platform.receivers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.core.extensions.isBeforeNow
import com.maciel.murillo.mutodo.core.extensions.safe
import com.maciel.murillo.mutodo.core.helper.NotificationHelper
import com.maciel.murillo.mutodo.modules.settings.domain.usecase.GetAlarmVibrateUseCase
import com.maciel.murillo.mutodo.modules.splash.presentation.SplashFragment
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
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
    private val getAlarmVibrateUseCase: GetAlarmVibrateUseCase by inject()

    override fun onReceive(context: Context, intent: Intent) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val builder = buildNotificationCompat(context, intent)
                throwNotification(context, builder, intent)
            } catch (e: Exception) {
                Log.e("AlarmReceiverError", "error -> $e")
            }
        }
    }

    private suspend fun throwNotification(context: Context, builder: NotificationCompat.Builder, intent: Intent) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(CHANNEL_ID_ALARM, context.getString(R.string.notification_title_task_alarms), NotificationManager.IMPORTANCE_HIGH).apply {
                    lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                    enableLights(true)
                    enableVibration(getAlarmVibrateUseCase.invoke())
                    setSound(Uri.parse("${ContentResolver.SCHEME_ANDROID_RESOURCE}://${context.packageName}/${R.raw.mu}"), AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .build())
                }
            )
            notificationManager.notify(NotificationHelper().TASK_NOTIFICATION_ID, buildNotification(context, intent).build())
        } else {
            notificationManager.notify(NotificationHelper().TASK_NOTIFICATION_ID, builder.build())
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun buildNotification(context: Context, intent: Intent): Notification.Builder {
        val resultIntent = Intent(context, SplashFragment::class.java)
        val resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = Notification.Builder(context, CHANNEL_ID_ALARM).apply {
                setSmallIcon(R.drawable.ic_cow_logo)
                setCategory(NotificationCompat.CATEGORY_ALARM)
                setContentIntent(resultPendingIntent)
                setAutoCancel(true)
                setColor(context.getColor(R.color.gray_dark_1))
        }

        setNotificationTitleAndDescription(context, intent, builder)

        return builder
    }

    private suspend fun buildNotificationCompat(context: Context, intent: Intent): NotificationCompat.Builder {
        val resultIntent = Intent(context, SplashFragment::class.java)
        val resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID_ALARM).apply {
                setSmallIcon(R.drawable.ic_cow_logo)
                setCategory(NotificationCompat.CATEGORY_ALARM)
                setContentIntent(resultPendingIntent)
                setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_LIGHTS )
                color = ContextCompat.getColor(context, R.color.gray_dark_1)
        }

        setNotificationTitleAndDescriptionCompat(context, intent, builder)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            setVibrationCompat(builder)
            builder.priority = NotificationCompat.PRIORITY_HIGH
            builder.setSound(Uri.parse("${ContentResolver.SCHEME_ANDROID_RESOURCE}://${context.packageName}/${R.raw.mu}"))
        }

        return builder
    }

    private suspend fun setNotificationTitleAndDescription(context: Context, intent: Intent, builder: Notification.Builder) {
        try {
            val tasks = getAllTasksUseCase.invoke().filter { task -> task.alarm?.dateTime?.isBeforeNow().safe()  }

            if (tasks.size <= 1) {
                getTaskTitleAndDescription(intent, builder)
            } else {
                setTextAndTitleFromList(context, builder, tasks)
            }

        } catch (e: Exception) {
            builder.setContentTitle(context.getString(R.string.new_task))
            builder.setContentText(context.getString(R.string.touch_to_check))
        }
    }

    private suspend fun setNotificationTitleAndDescriptionCompat(context: Context, intent: Intent, builder: NotificationCompat.Builder) {
        try {
            val tasks = getAllTasksUseCase.invoke().filter { task -> task.alarm?.dateTime?.isBeforeNow().safe()  }

            if (tasks.size <= 1) {
                getTaskTitleAndDescriptionCompat(intent, builder)
            } else {
                setTextAndTitleFromListCompat(context, builder, tasks)
            }

        } catch (e: Exception) {
            builder.setContentTitle(context.getString(R.string.new_task))
            builder.setContentText(context.getString(R.string.touch_to_check))
        }
    }

    private suspend fun getTaskTitleAndDescription(intent: Intent, builder: Notification.Builder) {
        val taskId = intent.getIntExtra(EXTRA_TASK_ID, -1)

        val task = getTaskByIdUseCase.invoke(taskId.toLong())
        builder.setContentTitle(task.title)

        if (task.description.isNotEmpty().safe()) {
            builder.setContentText(task.description)
        }
    }

    private suspend fun getTaskTitleAndDescriptionCompat(intent: Intent, builder: NotificationCompat.Builder) {
        val taskId = intent.getIntExtra(EXTRA_TASK_ID, -1)

        val task = getTaskByIdUseCase.invoke(taskId.toLong())
        builder.setContentTitle(task.title)

        if (task.description.isNotEmpty().safe()) {
            builder.setContentText(task.description)
        }
    }

    private fun setTextAndTitleFromList(context: Context, builder: Notification.Builder, tasks: List<Task>) {
        builder.setContentTitle("${tasks.size} ${context.getString(R.string.notification_title_task_alarms)}")
        builder.setContentText("${context.getString(R.string.most_recently_task)} ${tasks.last().title}")
    }

    private fun setTextAndTitleFromListCompat(context: Context, builder: NotificationCompat.Builder, tasks: List<Task>) {
        builder.setContentTitle("${tasks.size} ${context.getString(R.string.notification_title_task_alarms)}")
        builder.setContentText("${context.getString(R.string.most_recently_task)} ${tasks.last().title}")
    }

    private suspend fun setVibration(builder: Notification.Builder) {
        if (getAlarmVibrateUseCase.invoke()) {
            builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE or NotificationCompat.DEFAULT_LIGHTS)
        } else {
            builder.setDefaults(NotificationCompat.DEFAULT_LIGHTS)
        }
    }

    private suspend fun setVibrationCompat(builder: NotificationCompat.Builder) {
        if (getAlarmVibrateUseCase.invoke()) {
            builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE or NotificationCompat.DEFAULT_LIGHTS)
        } else {
            builder.setDefaults(NotificationCompat.DEFAULT_LIGHTS)
        }
    }
}