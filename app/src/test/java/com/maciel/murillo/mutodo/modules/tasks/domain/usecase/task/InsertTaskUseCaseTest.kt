package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task

import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Alarm
import com.maciel.murillo.mutodo.modules.tasks.domain.model.RepeatType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.ScheduleAlarmUseCase
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.InsertTaskUseCase
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class InsertTaskUseCaseTest {

    private val repository: TaskRepository = mockk(relaxed = true)
    private val scheduleAlarmUseCase: ScheduleAlarmUseCase = mockk(relaxed = true)

    private lateinit var insertTaskUseCase: InsertTaskUseCase

    @Before
    fun setUp() {
        insertTaskUseCase = InsertTaskUseCase(repository, scheduleAlarmUseCase)
    }

    @Test
    fun `should insert task from repository`() = runBlocking {
        val id = 10L
        val task = Task(
            id = -1,
            title = "title",
            description = "description",
            alarm = null,
            categoryType = CategoryType.ALL,
            insertingDate = "date",
            enabled = false,
        )
        coEvery { repository.insert(task) } returns id

        val result = insertTaskUseCase.invoke(task)

        coVerify { repository.insert(task) }
        confirmVerified(repository)
        assertEquals(task.id, id)
    }

    @Test
    fun `should schedule alarm if alarm exists`() = runBlocking {
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

        insertTaskUseCase.invoke(task)

        coVerify { scheduleAlarmUseCase.invoke(task) }
    }

    @Test
    fun `should not schedule alarm if alarm doesnt exists`() = runBlocking {
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

        insertTaskUseCase.invoke(task)

        coVerify { scheduleAlarmUseCase wasNot Called }
    }
}