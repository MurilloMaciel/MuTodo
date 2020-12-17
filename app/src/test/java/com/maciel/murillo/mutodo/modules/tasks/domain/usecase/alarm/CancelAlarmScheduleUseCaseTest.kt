package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm

import com.maciel.murillo.mutodo.modules.tasks.domain.repository.AlarmRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CancelAlarmScheduleUseCaseTest {

    private val repository: AlarmRepository = mockk(relaxed = true)

    private lateinit var cancelAlarmScheduleUseCase: CancelAlarmScheduleUseCase

    @Before
    fun setUp() {
        cancelAlarmScheduleUseCase = CancelAlarmScheduleUseCase(repository)
    }

    @Test
    fun `should get task from repository`() = runBlocking {
        val id = 10L
        coEvery { repository.cancelAlarm(id) } returns Unit

        val result = cancelAlarmScheduleUseCase.invoke(id)

        coVerify { repository.cancelAlarm(id) }
        confirmVerified(repository)
        kotlin.test.assertEquals(result, Unit)
    }
}