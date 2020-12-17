package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm

import com.maciel.murillo.mutodo.modules.tasks.domain.repository.AlarmRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class ScheduleAlarmUpdateUseCaseTest {

    private val repository: AlarmRepository = mockk(relaxed = true)

    private lateinit var scheduleAlarmUpdateUseCase: ScheduleAlarmUpdateUseCase

    @Before
    fun setUp() {
        scheduleAlarmUpdateUseCase = ScheduleAlarmUpdateUseCase(repository)
    }

    @Test
    fun `should get task from repository`() = runBlocking {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        coEvery { repository.updateAlarm(calendar) } returns Unit

        val result = scheduleAlarmUpdateUseCase.invoke()

        coVerify { repository.updateAlarm(calendar) }
        confirmVerified(repository)
        kotlin.test.assertEquals(result, Unit)
    }
}