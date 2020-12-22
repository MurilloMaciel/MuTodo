package com.maciel.murillo.mutodo.modules.tasks.presentation

interface TasksListener {

    fun onClickEdit(position: Int)
    fun onClickDelete(position: Int)
}