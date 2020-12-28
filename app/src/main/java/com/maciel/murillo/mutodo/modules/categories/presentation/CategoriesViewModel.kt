package com.maciel.murillo.mutodo.modules.categories.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciel.murillo.mutodo.core.extensions.safe
import com.maciel.murillo.mutodo.core.helper.Event
import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.CountByCategoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val countByCategoryUseCase: CountByCategoryUseCase
) : ViewModel() {

    private val _allTasksCount = MutableLiveData<Int>().apply { value = 0 }
    val allTasks: LiveData<Int> = _allTasksCount

    private val _workTaskCount = MutableLiveData<Int>().apply { value = 0 }
    val workTaskCount: LiveData<Int> = _workTaskCount

    private val _studiesTaskCount = MutableLiveData<Int>().apply { value = 0 }
    val studiesTaskCount: LiveData<Int> = _studiesTaskCount

    private val _personalTaskCount = MutableLiveData<Int>().apply { value = 0 }
    val personalTaskCount: LiveData<Int> = _personalTaskCount

    private val _gymTaskCount = MutableLiveData<Int>().apply { value = 0 }
    val gymTaskCount: LiveData<Int> = _gymTaskCount

    private val _generalTaskCount = MutableLiveData<Int>().apply { value = 0 }
    val generalTaskCount: LiveData<Int> = _generalTaskCount

    private val _funTaskCount = MutableLiveData<Int>().apply { value = 0 }
    val funTaskCount: LiveData<Int> = _funTaskCount

    private val _userNameGreetings = MutableLiveData<String>().apply { value = "" }
    val userNameGreetings: LiveData<String> = _userNameGreetings

    private val _allTasksText = MutableLiveData<String>().apply { value = "" }
    val allTasksText: LiveData<String> = _allTasksText

    private val _goToAllCategories = MutableLiveData<Event<Unit>>()
    val goToAllCategories: LiveData<Event<Unit>> = _goToAllCategories

    private val _goToSettings = MutableLiveData<Event<Unit>>()
    val goToSettings: LiveData<Event<Unit>> = _goToSettings

    fun getAllTasksCount() {
        viewModelScope.launch(Dispatchers.IO) {
            val workCount = countByCategoryUseCase.invoke(CategoryType.WORK)
            _workTaskCount.postValue(workCount)
            val studiesCount = countByCategoryUseCase.invoke(CategoryType.STUDIES)
            _studiesTaskCount.postValue(studiesCount)
            val personalCount = countByCategoryUseCase.invoke(CategoryType.PERSONAL)
            _personalTaskCount.postValue(personalCount)
            val gymCount = countByCategoryUseCase.invoke(CategoryType.GYM)
            _gymTaskCount.postValue(gymCount)
            val generalCount = countByCategoryUseCase.invoke(CategoryType.GENERAL)
            _generalTaskCount.postValue(generalCount)
            val funCount = countByCategoryUseCase.invoke(CategoryType.FUN)
            _funTaskCount.postValue(funCount)
            _allTasksCount.postValue(workCount + studiesCount + personalCount + gymCount + generalCount + funCount)
        }
    }

    fun onClickGoToAllCategories() {
        _goToAllCategories.postValue(Event(Unit))
    }

    fun onClickGoToSettings() {
        _goToSettings.postValue(Event(Unit))
    }

    fun replaceUserNameGreetings(greetings: String) {
        _userNameGreetings.postValue(greetings)
    }

    fun replaceAllTasksText(allTasksText: String) {
        _allTasksText.postValue(allTasksText)
    }
}