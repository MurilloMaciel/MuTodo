package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task

import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Alarm
import com.maciel.murillo.mutodo.modules.tasks.domain.model.RepeatType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.ScheduleAlarmUseCase
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.InsertTaskListUseCase
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class InsertTaskListUseCaseTest {

    private val repository: TaskRepository = mockk(relaxed = true)
    private val scheduleAlarmUseCase: ScheduleAlarmUseCase = mockk(relaxed = true)

    private lateinit var insertTaskListUseCase: InsertTaskListUseCase

    @Before
    fun setUp() {
        insertTaskListUseCase = InsertTaskListUseCase(repository, scheduleAlarmUseCase)
    }

    @Test
    fun `should insert a task list from repository`() = runBlocking {
        val tasks = listOf<Task>()
        coEvery { repository.insertAll(tasks) } returns Unit

        val result = insertTaskListUseCase.invoke(tasks)

        coVerify { repository.insertAll(tasks) }
        confirmVerified(repository)
        assertEquals(result, Unit)
    }

    @Test
    fun `should schedule alarms if exists`() = runBlocking {
        val alarm = Alarm(Calendar.getInstance(), RepeatType.NOT_REPEAT, "customDays")
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

        insertTaskListUseCase.invoke(tasks)

        coVerify { scheduleAlarmUseCase.invoke(task) }
    }

    @Test
    fun `should not schedule any alarm if none exists`() = runBlocking {
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

        insertTaskListUseCase.invoke(tasks)

        coVerify { scheduleAlarmUseCase wasNot Called }
    }
}