package com.maciel.murillo.mutodo.modules.settings.domain.usecase

import android.net.Uri
import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.ScheduleAlarmUpdateUseCase
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SetInitialSettingsUseCaseTest {

    private val repository: SettingsRepository = mockk(relaxed = true)
    private val scheduleAlarmUpdateUseCase: ScheduleAlarmUpdateUseCase = mockk(relaxed = true)

    private lateinit var setInitialSettingsUseCase: SetInitialSettingsUseCase

    @Before
    fun setUp() {
        setInitialSettingsUseCase = SetInitialSettingsUseCase(repository, scheduleAlarmUpdateUseCase)
    }

    @Test
    fun `should set initial settings from repository`() = runBlocking {
        // TODO: 17/12/2020 create test
//        val uriMock = mockk<Uri>()
//        setAlarmSoundUseCase.invoke(uriMock)
//
//        coVerify { repository.setAlarmSound(uriMock) }
//        confirmVerified(repository)
    }
}