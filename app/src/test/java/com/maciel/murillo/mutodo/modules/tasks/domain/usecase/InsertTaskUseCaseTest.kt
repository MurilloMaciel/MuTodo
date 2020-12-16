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

class InsertTaskUseCaseTest {

    private val repository: TaskRepository = mockk(relaxed = true)

    private lateinit var insertTaskUseCase: InsertTaskUseCase

    @Before
    fun setUp() {
        insertTaskUseCase = InsertTaskUseCase(repository)
    }

    @Test
    fun `should insert task from repository`() = runBlocking {
        val id = 10L
        val task = Task(
                id = id,
                title = "title",
                description = "description",
                alarm = null,
                insertingDate = "date",
                enabled = false,
        )
        coEvery { repository.insert(task) } returns Unit

        val result = insertTaskUseCase.invoke(task)

        coVerify { repository.insert(task) }
        confirmVerified(repository)
        assertEquals(result, Unit)
    }
}