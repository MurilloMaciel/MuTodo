package com.maciel.murillo.mutodo.modules.settings.domain.usecase

import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAlarmVibrateUseCaseTest {

    private val repository: SettingsRepository = mockk(relaxed = true)

    private lateinit var getAlarmVibrateUseCase: GetAlarmVibrateUseCase

    @Before
    fun setUp() {
        getAlarmVibrateUseCase = GetAlarmVibrateUseCase(repository)
    }

    @Test
    fun `should get alarm vibrate from repository`() = runBlocking {
        getAlarmVibrateUseCase.invoke()

        coVerify { repository.getAlarmVibrate() }
        confirmVerified(repository)
    }

}