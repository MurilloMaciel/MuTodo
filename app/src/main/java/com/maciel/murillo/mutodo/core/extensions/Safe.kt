package com.maciel.murillo.mutodo.core.extensions

import java.util.*

fun String?.safe() = this ?: ""

fun Int?.safe() = this ?: 0

fun Long?.safe() = this ?: 0

fun Double?.safe() = this ?: 0.0

fun Boolean?.safe() = this ?: false

fun <T> List<T>?.safe() = this ?: emptyList()

fun Calendar?.safe(): Calendar {
    return this ?: Calendar.getInstance().apply { add(Calendar.DAY_OF_YEAR, 1) }
}