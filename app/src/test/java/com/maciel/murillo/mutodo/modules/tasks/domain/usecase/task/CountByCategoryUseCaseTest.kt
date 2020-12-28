package com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task

import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.repository.TaskRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CountByCategoryUseCaseTest {

    private val repository: TaskRepository = mockk(relaxed = true)

    private lateinit var countByCategoryUseCase: CountByCategoryUseCase

    @Before
    fun setUp() {
        countByCategoryUseCase = CountByCategoryUseCase(repository)
    }

    @Test
    fun `should get count by category from repository`() = runBlocking {
        val count = 10
        val category = CategoryType.ALL
        coEvery { repository.countByCategory(category) } returns count

        val result = countByCategoryUseCase.invoke(category)

        coVerify { repository.countByCategory(category) }
        confirmVerified(repository)
        kotlin.test.assertEquals(result, count)
    }
}