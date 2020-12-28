package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task

import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Alarm
import com.maciel.murillo.mutodo.modules.tasks.domain.model.RepeatType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.GetTaskByIdUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class GetTaskByIdUseCaseTest {

    private val repository: TaskRepository = mockk(relaxed = true)

    private lateinit var getTaskByIdUseCase: GetTaskByIdUseCase

    @Before
    fun setUp() {
        getTaskByIdUseCase = GetTaskByIdUseCase(repository)
    }

    @Test
    fun `should get task from repository`() = runBlocking {
        val id = 10L
        val task = Task(
            id = id,
            title = "title",
            description = "description",
            alarm = null,
            categoryType = CategoryType.ALL,
            insertingDate = "date",
            enabled = false,
        )
        coEvery { repository.findById(id) } returns task

        val result = getTaskByIdUseCase.invoke(id)

        coVerify { repository.findById(id) }
        confirmVerified(repository)
        assertEquals(result, task)
    }
}