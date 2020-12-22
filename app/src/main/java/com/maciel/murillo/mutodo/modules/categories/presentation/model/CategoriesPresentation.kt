package com.maciel.murillo.mutodo.modules.categories.presentation.model

import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType

enum class CategoriesPresentation(
        val icon: Int,
        val backgroundColor: Int,
        val type: CategoryTypePresentation,
        val title: Int,
) {
    WORK(
        icon = R.drawable.ic_category_work_36,
        backgroundColor = R.color.indigo,
        type = CategoryTypePresentation.WORK,
        title = R.string.work_category_title,
    ),
    STUDIES(
        icon = R.drawable.ic_category_studies_36,
        backgroundColor = R.color.blue,
        type = CategoryTypePresentation.STUDIES,
        title = R.string.studies_category_title,
    ),
    GENERAL(
        icon = R.drawable.ic_category_general_36,
        backgroundColor = R.color.orange,
        type = CategoryTypePresentation.GENERAL,
        title = R.string.general_category_title,
    ),
    FUN(
        icon = R.drawable.ic_category_fun_36,
        backgroundColor = R.color.green_light,
        type = CategoryTypePresentation.FUN,
        title = R.string.fun_category_title,
    ),
    PERSONAL(
        icon = R.drawable.ic_category_personal_36,
        backgroundColor = R.color.brown,
        type = CategoryTypePresentation.PERSONAL,
        title = R.string.personal_category_title,
    ),
    GYM(
        icon = R.drawable.ic_category_gym_36,
        backgroundColor = R.color.pink,
        type = CategoryTypePresentation.GYM,
        title = R.string.gym_category_title,
    ),
}