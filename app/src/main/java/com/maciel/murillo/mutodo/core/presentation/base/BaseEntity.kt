package com.maciel.murillo.mutodo.core.presentation.base

abstract class BaseEntity {
    abstract var id: Long

    companion object {
        const val NO_ID: Long = -1
    }

    fun hasId() = id > NO_ID
}