package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task

import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllTasksByCategoryUseCaseTest {

    private val repository: TaskRepository = mockk(relaxed = true)

    private lateinit var getAllTasksByCategoryUseCase: GetAllTasksByCategoryUseCase

    @Before
    fun setUp() {
        getAllTasksByCategoryUseCase = GetAllTasksByCategoryUseCase(repository)
    }

    @Test
    fun `should get all tasks from repository`() = runBlocking {
        val tasks = listOf<Task>()
        val category = CategoryType.ALL
        coEvery { repository.findAllByCategory(category) } returns tasks

        val result = getAllTasksByCategoryUseCase.invoke(category)

        coVerify { repository.findAllByCategory(category) }
        confirmVerified(repository)
        kotlin.test.assertEquals(result, tasks)
    }
}