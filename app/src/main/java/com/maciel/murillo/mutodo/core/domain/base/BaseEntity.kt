package com.maciel.murillo.mutodo.core.domain.base

abstract class BaseEntity {
    abstract var id: Long?

    companion object {
        val NO_ID = null
    }

    fun hasId() = id != NO_ID
}