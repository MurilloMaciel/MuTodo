package com.maciel.murillo.mutodo.modules.categories.presentation.model

import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType

data class CategoryPresentation(
        val categoryPresentation: CategoriesPresentation,
        var remaining: Int? = null,
        var order: Int
) {
        companion object {
                fun getCategories() = listOf(
                        CategoryPresentation(
                                categoryPresentation = CategoriesPresentation.FUN,
                                remaining = 0,
                                order = 6
                        ),
                        CategoryPresentation(
                                categoryPresentation = CategoriesPresentation.GENERAL,
                                remaining = 0,
                                order = 5
                        ),
                        CategoryPresentation(
                                categoryPresentation = CategoriesPresentation.GYM,
                                remaining = 0,
                                order = 4
                        ),
                        CategoryPresentation(
                                categoryPresentation = CategoriesPresentation.PERSONAL,
                                remaining = 0,
                                order = 3
                        ),
                        CategoryPresentation(
                                categoryPresentation = CategoriesPresentation.STUDIES,
                                remaining = 0,
                                order = 2
                        ),
                        CategoryPresentation(
                                categoryPresentation = CategoriesPresentation.WORK,
                                remaining = 0,
                                order = 1
                        ),
                )
        }
}

