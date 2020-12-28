package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task

import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Alarm
import com.maciel.murillo.mutodo.modules.tasks.domain.model.RepeatType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.CancelAlarmScheduleUseCase
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.UpdateAlarmScheduleUseCase
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class UpdateTaskUseCaseTest {

    private val repository: TaskRepository = mockk(relaxed = true)
    private val updateAlarmScheduleUseCase: UpdateAlarmScheduleUseCase = mockk(relaxed = true)
    private val cancelAlarmScheduleUseCase: CancelAlarmScheduleUseCase = mockk(relaxed = true)

    private lateinit var updateTaskUseCase: UpdateTaskUseCase

    @Before
    fun setUp() {
        updateTaskUseCase = UpdateTaskUseCase(repository, updateAlarmScheduleUseCase, cancelAlarmScheduleUseCase)
    }

    @Test
    fun `should update task from repository`() = runBlocking {
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
        coEvery { repository.update(task) } returns Unit

        val result = updateTaskUseCase.invoke(task)

        coVerify { repository.update(task) }
        confirmVerified(repository)
        kotlin.test.assertEquals(result, Unit)
    }

    @Test
    fun `should update alarm schedule if alarm exists`() = runBlocking {
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

        updateTaskUseCase.invoke(task)

        coVerify { updateAlarmScheduleUseCase.invoke(task) }
    }

    @Test
    fun `should cancel alarm schedule if alarm doesnt exists`() = runBlocking {
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

        updateTaskUseCase.invoke(task)

        coVerify { cancelAlarmScheduleUseCase.invoke(task) }
    }
}