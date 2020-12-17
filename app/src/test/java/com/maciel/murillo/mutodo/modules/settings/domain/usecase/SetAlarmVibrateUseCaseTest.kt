package com.maciel.murillo.mutodo.modules.settings.domain.usecase

import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SetAlarmVibrateUseCaseTest {

    private val repository: SettingsRepository = mockk(relaxed = true)

    private lateinit var setAlarmVibrateUseCase: SetAlarmVibrateUseCase

    @Before
    fun setUp() {
        setAlarmVibrateUseCase = SetAlarmVibrateUseCase(repository)
    }

    @Test
    fun `should get alarm vibrate from repository`() = runBlocking {
        setAlarmVibrateUseCase.invoke(true)

        coVerify { repository.setAlarmVibrate(true) }
        confirmVerified(repository)
    }
}