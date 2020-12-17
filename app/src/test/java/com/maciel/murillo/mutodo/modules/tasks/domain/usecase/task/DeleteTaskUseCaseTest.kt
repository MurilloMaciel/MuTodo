package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task

import com.maciel.murillo.mutodo.modules.tasks.domain.model.Alarm
import com.maciel.murillo.mutodo.modules.tasks.domain.model.RepeatType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.CancelAlarmScheduleUseCase
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.DeleteTaskUseCase
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class DeleteTaskUseCaseTest {

    private val repository: TaskRepository = mockk(relaxed = true)
    private val cancelAlarmScheduleUseCase: CancelAlarmScheduleUseCase = mockk(relaxed = true)

    private lateinit var deleteTaskUseCase: DeleteTaskUseCase

    @Before
    fun setUp() {
        deleteTaskUseCase = DeleteTaskUseCase(repository, cancelAlarmScheduleUseCase)
    }

    @Test
    fun `should delete task from repository`() = runBlocking {
        val id = 10L
        val task = Task(
                id = id,
                title = "title",
                description = "description",
                alarm = null,
                insertingDate = "date",
                enabled = false,
        )
        coEvery { repository.delete(id) } returns Unit

        val result = deleteTaskUseCase.invoke(task)

        coVerify { repository.delete(id) }
        confirmVerified(repository)
        assertEquals(result, Unit)
    }

    @Test
    fun `should cancel alarm schedule if alarm exists`() = runBlocking {
        val alarm = Alarm(Calendar.getInstance(), RepeatType.NOT_REPEAT, "customDays")
        val task = Task(
                id = 10L,
                title = "title",
                description = "description",
                alarm = alarm,
                insertingDate = "date",
                enabled = false,
        )

        deleteTaskUseCase.invoke(task)

        coVerify { cancelAlarmScheduleUseCase.invoke(task.id) }
    }

    @Test
    fun `should not cancel alarm schedule if alarm doesnt exists`() = runBlocking {
        val task = Task(
                id = 10L,
                title = "title",
                description = "description",
                alarm = null,
                insertingDate = "date",
                enabled = false,
        )

        deleteTaskUseCase.invoke(task)

        coVerify { cancelAlarmScheduleUseCase wasNot Called }
    }
}