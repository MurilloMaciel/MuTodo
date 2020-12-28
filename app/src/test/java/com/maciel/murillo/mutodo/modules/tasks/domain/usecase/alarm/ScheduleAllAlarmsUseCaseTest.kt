package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm

import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Alarm
import com.maciel.murillo.mutodo.modules.tasks.domain.model.RepeatType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.GetAllTasksUseCase
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class ScheduleAllAlarmsUseCaseTest {

    private val getAllTasksUseCase: GetAllTasksUseCase = mockk(relaxed = true)
    private val scheduleAlarmUseCase: ScheduleAlarmUseCase = mockk(relaxed = true)

    private lateinit var scheduleAllAlarmsUseCase: ScheduleAllAlarmsUseCase

    @Before
    fun setUp() {
        scheduleAllAlarmsUseCase = ScheduleAllAlarmsUseCase(getAllTasksUseCase, scheduleAlarmUseCase)
    }

    @Test
    fun `should schedule alarm if is old and has repeat from repository`() = runBlocking {
        val alarm = Alarm(Calendar.getInstance().apply { add(Calendar.DATE, -2) }, RepeatType.DAY, "customDays")
        val id = 10L
        val task = Task(
            id = id,
            title = "title",
            description = "description",
            alarm = alarm,
            categoryType = CategoryType.ALL,
            insertingDate = "date",
            enabled = false,
        )
        val tasks = listOf<Task>(task)
        coEvery { getAllTasksUseCase.invoke() } returns tasks

        scheduleAllAlarmsUseCase.invoke()

        coVerify { scheduleAlarmUseCase.invoke(task) }
    }

    @Test
    fun `should schedule alarm if is before now from repository`() = runBlocking {
        val alarm = Alarm(Calendar.getInstance().apply { add(Calendar.DATE, 2) }, RepeatType.NOT_REPEAT, "customDays")
        val id = 10L
        val task = Task(
            id = id,
            title = "title",
            description = "description",
            alarm = alarm,
            categoryType = CategoryType.ALL,
            insertingDate = "date",
            enabled = false,
        )
        val tasks = listOf<Task>(task)
        coEvery { getAllTasksUseCase.invoke() } returns tasks

        scheduleAllAlarmsUseCase.invoke()

        coVerify { scheduleAlarmUseCase.invoke(task) }
    }

    @Test
    fun `should not schedule alarm if is old and has no repeat from repository`() = runBlocking {
        val alarm = Alarm(Calendar.getInstance().apply { add(Calendar.DATE, -2) }, RepeatType.NOT_REPEAT, "customDays")
        val id = 10L
        val task = Task(
            id = id,
            title = "title",
            description = "description",
            alarm = alarm,
            categoryType = CategoryType.ALL,
            insertingDate = "date",
            enabled = false,
        )
        val tasks = listOf<Task>(task)
        coEvery { getAllTasksUseCase.invoke() } returns tasks

        scheduleAllAlarmsUseCase.invoke()

        coVerify { scheduleAlarmUseCase wasNot Called }
    }

    @Test
    fun `should not schedule alarm if is null from repository`() = runBlocking {
        val id = 10L
        val task = Task(
            id = id,
            title = "title",
            description = "description",
            alarm = null,
            categoryType = CategoryType.ALL,
            insertingDate = "date",
            enabled = false,
        )
        val tasks = listOf<Task>(task)
        coEvery { getAllTasksUseCase.invoke() } returns tasks

        scheduleAllAlarmsUseCase.invoke()

        coVerify { scheduleAlarmUseCase wasNot Called }
    }
}