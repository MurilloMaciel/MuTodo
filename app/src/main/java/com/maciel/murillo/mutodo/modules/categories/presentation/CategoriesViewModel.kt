package com.maciel.murillo.mutodo.modules.categories.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CategoriesViewModel : ViewModel() {

    private val _allTasksRemaining = MutableLiveData<Int>().apply { value = 0 }
    val allTasksRemaining: LiveData<Int> = _allTasksRemaining

//    private val _allTasksRemaining = MutableLiveData<Int>().apply { value = 0 }
//    val allTasksRemaining: LiveData<Int> = _allTasksRemaining

    private fun setAllTasksRemaining() {
        _allTasksRemaining.postValue(1)
    }
}