package com.maciel.murillo.mutodo.modules.tasks.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maciel.murillo.mutodo.core.extensions.safe
import com.maciel.murillo.mutodo.core.extensions.toDateTimeString
import com.maciel.murillo.mutodo.core.domain.base.BaseEntity
import com.maciel.murillo.mutodo.modules.categories.data.model.*
import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import java.util.*

@Entity
data class TaskData(
        @PrimaryKey @ColumnInfo(name = "id") val id: Long? = BaseEntity.NO_ID,
        @ColumnInfo(name = "title") val title: String?,
        @ColumnInfo(name = "description") val description: String?,
        @ColumnInfo(name = "categoryType") val categoryType: String?,
        @ColumnInfo(name = "insertingDate") val insertingDate: String? = null,
        @Embedded val alarm: AlarmData? = null,
        @ColumnInfo(name = "enabled") val enabled: Boolean = true,
)

fun TaskData.mapToTask() = Task(
        id = id,
        title = title.safe(),
        description = description.safe(),
        categoryType = categoryType?.toCategoryType() ?: CategoryType.GENERAL,
        alarm = alarm?.mapToAlarm(),
        insertingDate = insertingDate,
        enabled = enabled
)

fun Task.mapToTaskData() = TaskData(
        id = id,
        title = title,
        description = description,
        categoryType = categoryType.toEnumString(),
        insertingDate = insertingDate,
        alarm = alarm?.mapToAlarmData(),
        enabled = enabled
)