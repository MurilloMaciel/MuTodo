package com.maciel.murillo.mutodo.modules.categories.data.model

import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType

private const val DB_ALL = "ALL"
private const val DB_WORK = "WORK"
private const val DB_STUDIES = "STUDIES"
private const val DB_GENERAL = "GENERAL"
private const val DB_FUN = "FUN"
private const val DB_PERSONAL = "PERSONAL"
private const val DB_GYM = "GYM"

enum class CategoryTypeData {

    ALL,
    WORK,
    STUDIES,
    GENERAL,
    FUN,
    PERSONAL,
    GYM;
}

fun CategoryTypeData.mapToCategoryType() = when (this) {
    CategoryTypeData.ALL -> CategoryType.ALL
    CategoryTypeData.WORK -> CategoryType.WORK
    CategoryTypeData.STUDIES -> CategoryType.STUDIES
    CategoryTypeData.GENERAL -> CategoryType.GENERAL
    CategoryTypeData.FUN -> CategoryType.FUN
    CategoryTypeData.PERSONAL -> CategoryType.PERSONAL
    CategoryTypeData.GYM -> CategoryType.GYM
}

fun CategoryType.mapToCategoryTypeData() = when (this) {
    CategoryType.ALL -> CategoryTypeData.ALL
    CategoryType.WORK -> CategoryTypeData.WORK
    CategoryType.STUDIES -> CategoryTypeData.STUDIES
    CategoryType.GENERAL -> CategoryTypeData.GENERAL
    CategoryType.FUN -> CategoryTypeData.FUN
    CategoryType.PERSONAL -> CategoryTypeData.PERSONAL
    CategoryType.GYM -> CategoryTypeData.GYM
}

fun CategoryType.toEnumString() = when (this) {
    CategoryType.ALL -> DB_ALL
    CategoryType.WORK -> DB_WORK
    CategoryType.STUDIES -> DB_STUDIES
    CategoryType.GENERAL -> DB_GENERAL
    CategoryType.FUN -> DB_FUN
    CategoryType.PERSONAL -> DB_PERSONAL
    CategoryType.GYM -> DB_GYM
}

fun String.toCategoryType() = when (this) {
    DB_ALL -> CategoryType.ALL
    DB_WORK -> CategoryType.WORK
    DB_STUDIES -> CategoryType.STUDIES
    DB_GENERAL -> CategoryType.GENERAL
    DB_FUN -> CategoryType.FUN
    DB_PERSONAL -> CategoryType.PERSONAL
    DB_GYM -> CategoryType.GYM
    else -> CategoryType.GENERAL
}
