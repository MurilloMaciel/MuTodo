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

class DeleteTaskUseCaseTest {

    private val repository: TaskRepository = mockk(relaxed = true)

    private lateinit var deleteTaskUseCase: DeleteTaskUseCase

    @Before
    fun setUp() {
        deleteTaskUseCase = DeleteTaskUseCase(repository)
    }

    @Test
    fun `should delete task from repository`() = runBlocking {
        val id = 10L
        val task = Task(
                id = id,
                title = "title",
                description = "description",
                alarm = null,
                insertingDate = "date",
                enabled = false,
        )
        coEvery { repository.delete(id) } returns Unit

        val result = deleteTaskUseCase.invoke(task)

        coVerify { repository.delete(id) }
        confirmVerified(repository)
        assertEquals(result, Unit)
    }
}