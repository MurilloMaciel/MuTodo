package com.maciel.murillo.mutodo.core.extensions

fun Any?.isNull(): Boolean {
    return this == null
}

fun Any?.isNotNull(): Boolean {
    return this != null
}
