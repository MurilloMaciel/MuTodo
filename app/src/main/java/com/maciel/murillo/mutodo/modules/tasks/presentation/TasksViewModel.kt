package com.maciel.murillo.mutodo.modules.tasks.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciel.murillo.mutodo.core.extensions.log
import com.maciel.murillo.mutodo.core.helper.Event
import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.DeleteTaskUseCase
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.GetAllTasksByCategoryUseCase
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.GetAllTasksUseCase
import kotlinx.coroutines.launch

class TasksViewModel(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getAllTasksByCategoryUseCase: GetAllTasksByCategoryUseCase,
) : ViewModel() {

    private var categoryType: CategoryType = CategoryType.GENERAL
    var tasks = arrayListOf<Task>()

    private val _updateTasks = MutableLiveData<Event<Unit>>()
    val updateTasks: LiveData<Event<Unit>> = _updateTasks

    private val _goBack = MutableLiveData<Event<Unit>>()
    val goBack: LiveData<Event<Unit>> = _goBack

    private val _addOrUpdateTask = MutableLiveData<Event<Long>>()
    val addOrUpdateTask: LiveData<Event<Long>> = _addOrUpdateTask

    private fun getTasks() {
        viewModelScope.launch {
            tasks = if (categoryType == CategoryType.ALL) {
                ArrayList(getAllTasksUseCase.invoke())
            } else {
                ArrayList(getAllTasksByCategoryUseCase.invoke(categoryType))
            }
            _updateTasks.postValue(Event(Unit))
        }
    }

    fun onInitializeScreen(categoryType: CategoryType) {
        this.categoryType = categoryType
        getTasks()
    }

    fun onClickDeleteTask(position: Int) {
        viewModelScope.launch {
            deleteTaskUseCase.invoke(tasks[position])
            tasks.removeAt(position)
            _updateTasks.postValue(Event(Unit))
        }
    }

    fun onClickEditTask(position: Int) {
        _addOrUpdateTask.postValue(Event(tasks[position].id ?: -1L))
    }

    fun onClickAddTask() {
        _addOrUpdateTask.postValue(Event(-1L))
    }

    fun onBackClick() {
        _goBack.postValue(Event(Unit))
    }
}