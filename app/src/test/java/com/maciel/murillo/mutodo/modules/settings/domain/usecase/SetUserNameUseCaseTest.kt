package com.maciel.murillo.mutodo.modules.settings.domain.usecase

import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SetUserNameUseCaseTest {

    private val repository: SettingsRepository = mockk(relaxed = true)

    private lateinit var setUserNameUseCase: SetUserNameUseCase

    @Before
    fun setUp() {
        setUserNameUseCase = SetUserNameUseCase(repository)
    }

    @Test
    fun `should set user name from repository`() = runBlocking {
        val userName = "userName"

        setUserNameUseCase.invoke(userName)

        coVerify { repository.setUserName(userName) }
        confirmVerified(repository)
    }
}