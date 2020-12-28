package com.maciel.murillo.mutodo.modules.categories.presentation.model

data class CategoryPresentation(
    val category: CategoriesPresentation,
    var count: Int? = null,
    var order: Int
) {
        companion object {
                fun getCategories() = listOf(
                        CategoryPresentation(
                                category = CategoriesPresentation.FUN,
                                count = 0,
                                order = 6
                        ),
                        CategoryPresentation(
                                category = CategoriesPresentation.GENERAL,
                                count = 0,
                                order = 5
                        ),
                        CategoryPresentation(
                                category = CategoriesPresentation.GYM,
                                count = 0,
                                order = 4
                        ),
                        CategoryPresentation(
                                category = CategoriesPresentation.PERSONAL,
                                count = 0,
                                order = 3
                        ),
                        CategoryPresentation(
                                category = CategoriesPresentation.STUDIES,
                                count = 0,
                                order = 2
                        ),
                        CategoryPresentation(
                                category = CategoriesPresentation.WORK,
                                count = 0,
                                order = 1
                        ),
                )
        }
}

