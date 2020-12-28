package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm

import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Alarm
import com.maciel.murillo.mutodo.modules.tasks.domain.model.RepeatType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.AlarmRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class ScheduleAlarmUseCaseTest {

    private val repository: AlarmRepository = mockk(relaxed = true)

    private lateinit var scheduleAlarmUseCase: ScheduleAlarmUseCase

    @Before
    fun setUp() {
        scheduleAlarmUseCase = ScheduleAlarmUseCase(repository)
    }

    @Test
    fun `should schedule alarm from repository`() = runBlocking {
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
        coEvery { repository.scheduleAlarm(task) } returns Unit

        val result = scheduleAlarmUseCase.invoke(task)

        coVerify { repository.scheduleAlarm(task) }
        confirmVerified(repository)
        kotlin.test.assertEquals(result, Unit)
    }
}