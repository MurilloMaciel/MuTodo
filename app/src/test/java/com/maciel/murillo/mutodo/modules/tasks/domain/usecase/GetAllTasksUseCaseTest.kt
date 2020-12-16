package com.maciel.murillo.mutodo.modules.tasks.domain.usecase

import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetAllTasksUseCaseTest {

    private val repository: TaskRepository = mockk(relaxed = true)

    private lateinit var getAllTasksUseCase: GetAllTasksUseCase

    @Before
    fun setUp() {
        getAllTasksUseCase = GetAllTasksUseCase(repository)
    }

    @Test
    fun `should get all tasks from repository`() = runBlocking {
        val tasks = listOf<Task>()
        coEvery { repository.findAll() } returns tasks

        val result = getAllTasksUseCase.invoke()

        coVerify { repository.findAll() }
        confirmVerified(repository)
        assertEquals(result, tasks)
    }
}