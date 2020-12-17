package com.maciel.murillo.mutodo.modules.settings.domain.usecase

import android.net.Uri
import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SetAlarmSoundUseCaseTest {

    private val repository: SettingsRepository = mockk(relaxed = true)

    private lateinit var setAlarmSoundUseCase: SetAlarmSoundUseCase

    @Before
    fun setUp() {
        setAlarmSoundUseCase = SetAlarmSoundUseCase(repository)
    }

    @Test
    fun `should set alarm sound from repository`() = runBlocking {
        val uriMock = mockk<Uri>()
        setAlarmSoundUseCase.invoke(uriMock)

        coVerify { repository.setAlarmSound(uriMock) }
        confirmVerified(repository)
    }
}