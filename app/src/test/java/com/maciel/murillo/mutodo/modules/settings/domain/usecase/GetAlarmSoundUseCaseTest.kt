package com.maciel.murillo.mutodo.modules.settings.domain.usecase

import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAlarmSoundUseCaseTest {

    private val repository: SettingsRepository = mockk(relaxed = true)

    private lateinit var getAlarmSoundUseCase: GetAlarmSoundUseCase

    @Before
    fun setUp() {
        getAlarmSoundUseCase = GetAlarmSoundUseCase(repository)
    }

    @Test
    fun `should get alarm sound from repository`() = runBlocking {
        getAlarmSoundUseCase.invoke()

        coVerify { repository.getAlarmSound() }
        confirmVerified(repository)
    }
}