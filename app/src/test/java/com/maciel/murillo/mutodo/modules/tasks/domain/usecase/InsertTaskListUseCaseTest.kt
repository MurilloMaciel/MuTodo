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

class InsertTaskListUseCaseTest {

    private val repository: TaskRepository = mockk(relaxed = true)

    private lateinit var insertTaskListUseCase: InsertTaskListUseCase

    @Before
    fun setUp() {
        insertTaskListUseCase = InsertTaskListUseCase(repository)
    }

    @Test
    fun `should insert a task list from repository`() = runBlocking {
        val tasks = listOf<Task>()
        coEvery { repository.insertAll(tasks) } returns Unit

        val result = insertTaskListUseCase.invoke(tasks)

        coVerify { repository.insertAll(tasks) }
        confirmVerified(repository)
        assertEquals(result, Unit)
    }
}