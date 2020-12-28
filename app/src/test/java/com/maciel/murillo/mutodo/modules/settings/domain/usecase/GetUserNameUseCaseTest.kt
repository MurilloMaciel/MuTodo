package com.maciel.murillo.mutodo.modules.settings.domain.usecase

import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetUserNameUseCaseTest {

    private val repository: SettingsRepository = mockk(relaxed = true)

    private lateinit var getUserNameUseCase: GetUserNameUseCase

    @Before
    fun setUp() {
        getUserNameUseCase = GetUserNameUseCase(repository)
    }

    @Test
    fun `should get user name from repository`() = runBlocking {
        val userName = "userName"
        coEvery { repository.getUserName() } returns userName

        val result = getUserNameUseCase.invoke()

        coVerify { repository.getUserName() }
        confirmVerified(repository)
        assertEquals(result, userName)
    }
}