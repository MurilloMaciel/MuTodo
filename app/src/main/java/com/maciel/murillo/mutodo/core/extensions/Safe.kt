package com.maciel.murillo.mutodo.core.extensions

fun String?.safe() = this ?: ""

fun Int?.safe() = this ?: 0

fun Long?.safe() = this ?: 0

fun Double?.safe() = this ?: 0.0

fun Boolean?.safe() = this ?: false

fun <T> List<T>?.safe() = this ?: emptyList()

fun <T> T?.isNull() = (this == null)