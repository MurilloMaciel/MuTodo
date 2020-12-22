package com.maciel.murillo.mutodo.modules.categories.presentation.model

import com.maciel.murillo.mutodo.R
import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType

enum class CategoryTypePresentation {

    ALL,
    WORK,
    STUDIES,
    GENERAL,
    FUN,
    PERSONAL,
    GYM
}

fun CategoryType.mapToCategoryTypePresentation() = when (this) {
    CategoryType.ALL -> CategoryTypePresentation.ALL
    CategoryType.WORK -> CategoryTypePresentation.WORK
    CategoryType.STUDIES -> CategoryTypePresentation.STUDIES
    CategoryType.GENERAL -> CategoryTypePresentation.GENERAL
    CategoryType.FUN -> CategoryTypePresentation.FUN
    CategoryType.PERSONAL -> CategoryTypePresentation.PERSONAL
    CategoryType.GYM -> CategoryTypePresentation.GYM
}

fun CategoryTypePresentation.mapToCategoryType() = when (this) {
    CategoryTypePresentation.ALL -> CategoryType.ALL
    CategoryTypePresentation.WORK -> CategoryType.WORK
    CategoryTypePresentation.STUDIES -> CategoryType.STUDIES
    CategoryTypePresentation.GENERAL -> CategoryType.GENERAL
    CategoryTypePresentation.FUN -> CategoryType.FUN
    CategoryTypePresentation.PERSONAL -> CategoryType.PERSONAL
    CategoryTypePresentation.GYM -> CategoryType.GYM
}

fun CategoryTypePresentation.getCategoryNameResource() = when (this) {
    CategoryTypePresentation.ALL -> R.string.all_category_title
    CategoryTypePresentation.WORK -> R.string.work_category_title
    CategoryTypePresentation.STUDIES -> R.string.studies_category_title
    CategoryTypePresentation.GENERAL -> R.string.general_category_title
    CategoryTypePresentation.FUN -> R.string.fun_category_title
    CategoryTypePresentation.PERSONAL -> R.string.personal_category_title
    CategoryTypePresentation.GYM -> R.string.gym_category_title
}