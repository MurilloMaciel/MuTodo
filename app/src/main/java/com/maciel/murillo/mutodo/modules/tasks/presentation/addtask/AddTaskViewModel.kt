package com.maciel.murillo.mutodo.modules.tasks.presentation.addtask

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciel.murillo.mutodo.core.extensions.*
import com.maciel.murillo.mutodo.core.helper.Event
import com.maciel.murillo.mutodo.core.helper.formatTimeWithAndroidFormat
import com.maciel.murillo.mutodo.core.helper.getFullDateName
import com.maciel.murillo.mutodo.modules.categories.domain.model.CategoryType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Alarm
import com.maciel.murillo.mutodo.modules.tasks.domain.model.RepeatType
import com.maciel.murillo.mutodo.modules.tasks.domain.model.Task
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.GetTaskByIdUseCase
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.InsertTaskUseCase
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.task.UpdateTaskUseCase
import kotlinx.coroutines.launch
import java.util.*

class AddTaskViewModel(
    private val insertTaskUseCase: InsertTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
) : ViewModel() {

    private var categoryType: CategoryType = CategoryType.GENERAL
    var taskToEdit: Task? = null

    private val _taskTitle = MutableLiveData("")
    val taskTitle: LiveData<String> = _taskTitle

    private val _taskDescription = MutableLiveData("")
    val taskDescription: LiveData<String> = _taskDescription

    private val _submitEnabled = MutableLiveData(false)
    val submitEnabled: LiveData<Boolean> = _submitEnabled

    private val _taskInsertFinish = MutableLiveData<Event<Boolean>>()
    val taskInsertFinish: LiveData<Event<Boolean>> = _taskInsertFinish

    private val _goBack = MutableLiveData<Event<Unit>>()
    val goBack: LiveData<Event<Unit>> = _goBack

    private var _taskAlarmCalendar = MutableLiveData<Calendar>().apply { postValue(Calendar.getInstance()) }

    private val _alarmDate = MutableLiveData<String>()
    val alarmDate: LiveData<String> = _alarmDate

    private val _alarmTime = MutableLiveData<String>()
    val alarmTime: LiveData<String> = _alarmTime

    val alarmEnabled = MutableLiveData<Boolean>()

    val alarmRepeatAlwaysEnabled = MutableLiveData<Boolean>()

    private val _pickDate = MutableLiveData<Event<Calendar>>()
    val pickDate: LiveData<Event<Calendar>> = _pickDate

    private val _pickTime = MutableLiveData<Event<Calendar>>()
    val pickTime: LiveData<Event<Calendar>> = _pickTime

    private fun checkSubmitEnable() {
        _submitEnabled.postValue(
            _taskTitle.value.safe().isNotEmpty() && _taskDescription.value.safe().isNotEmpty()
        )
    }

    private fun getTaskToInsert() = Task(
        title = _taskTitle.value.safe(),
        description = _taskDescription.value.safe(),
        categoryType = categoryType,
        insertingDate = Calendar.getInstance().toDateTimeString(),
        alarm = if (alarmEnabled.value.safe()) Alarm(
            dateTime = _taskAlarmCalendar.value.safe(),
            repeatType = if (alarmRepeatAlwaysEnabled.value.safe()) RepeatType.DAY else RepeatType.NOT_REPEAT,
        ) else null
    )

    private fun getAlarmToUpdateTask(): Alarm? {
        return if (alarmEnabled.value.safe()) {
            Alarm(
                dateTime = _taskAlarmCalendar.value.safe(),
                repeatType = if (alarmRepeatAlwaysEnabled.value.safe()) RepeatType.DAY else RepeatType.NOT_REPEAT
            )
        } else null
    }

    private fun getTaskToUpdate() = Task(
        id = taskToEdit?.id.safe(),
        title = _taskTitle.value.safe(),
        description = _taskDescription.value.safe(),
        categoryType = categoryType,
        alarm = getAlarmToUpdateTask(),
        insertingDate = Calendar.getInstance().toDateTimeString(),
    )

    private fun insertTask() {
        viewModelScope.launch {
            insertTaskUseCase.invoke(getTaskToInsert())
            _taskInsertFinish.postValue(Event(true))
        }
    }

    private fun updateTask() {
        viewModelScope.launch {
            updateTaskUseCase.invoke(getTaskToUpdate())
            _taskInsertFinish.postValue(Event(true))
        }
    }

    private fun setInitialValuesForEditTask() {
        _taskTitle.postValue(taskToEdit?.title.safe())
        _taskDescription.postValue(taskToEdit?.description.safe())
        taskToEdit?.alarm?.run {
            alarmEnabled.postValue(true)
            _taskAlarmCalendar.value = this.dateTime
            _alarmDate.value = _taskAlarmCalendar.value?.getDateToString()
            _alarmTime.value = _taskAlarmCalendar.value?.getTimeToString()
            alarmRepeatAlwaysEnabled.postValue(this.repeatType == RepeatType.DAY)
        } ?: alarmEnabled.postValue(false)
    }

    private fun handleWithUpdateTaskIfHasId(taskToEditId: Long?) {
        taskToEditId?.run {
            viewModelScope.launch {
                taskToEdit = getTaskByIdUseCase.invoke(taskToEditId)
                setInitialValuesForEditTask()
            }
        }
    }

    fun onInitializeScreen(categoryType: CategoryType, taskId: Long?, dateText: String, timeText: String) {
        handleWithUpdateTaskIfHasId(taskId)
        this.categoryType = categoryType
        _alarmDate.postValue(dateText)
        _alarmTime.postValue(timeText)
    }

    fun onAlarmDateSet(year: Int, month: Int, day: Int) {
        val calendar = _taskAlarmCalendar.value.safe()
        _taskAlarmCalendar.postValue(calendar.apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
        })
        _alarmDate.postValue(_taskAlarmCalendar.value?.getDateToString())
    }

    fun onAlarmTimeSet(hour: Int, minute: Int) {
        val calendar = _taskAlarmCalendar.value.safe()
        _taskAlarmCalendar.postValue(calendar.apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
        })
        _alarmTime.postValue(_taskAlarmCalendar.value?.getTimeToString())
    }

    fun onClickDate() {
        _pickDate.postValue(Event(_taskAlarmCalendar.value.safe()))
    }

    fun onClickTime() {
        _pickTime.postValue(Event(_taskAlarmCalendar.value.safe()))
    }

    fun onChangeTitle(title: String) {
        _taskTitle.value = title
        checkSubmitEnable()
    }

    fun onChangeDescription(description: String) {
        _taskDescription.value = description
        checkSubmitEnable()
    }

    fun onSubmitClick() {
        if (taskToEdit.isNull()) {
            insertTask()
        } else {
            updateTask()
        }
    }

    fun onBackClick() {
        _goBack.postValue(Event(Unit))
    }
}
