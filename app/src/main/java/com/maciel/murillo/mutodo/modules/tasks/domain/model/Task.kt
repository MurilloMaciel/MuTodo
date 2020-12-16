package com.maciel.murillo.mutodo.modules.tasks.domain.model

import com.maciel.murillo.mutodo.core.presentation.base.BaseEntity

data class Task(
        override var id: Long = NO_ID,
        val title: String,
        val description: String,
        val alarm: Alarm? = null,
        val insertingDate: String? = null,
        val enabled: Boolean = true,
) : BaseEntity()