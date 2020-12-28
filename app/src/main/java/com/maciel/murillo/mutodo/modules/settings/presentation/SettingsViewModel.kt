package com.maciel.murillo.mutodo.modules.settings.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciel.murillo.mutodo.core.extensions.isNull
import com.maciel.murillo.mutodo.core.extensions.safe
import com.maciel.murillo.mutodo.core.helper.Event
import com.maciel.murillo.mutodo.modules.settings.domain.usecase.GetAlarmVibrateUseCase
import com.maciel.murillo.mutodo.modules.settings.domain.usecase.GetUserNameUseCase
import com.maciel.murillo.mutodo.modules.settings.domain.usecase.SetAlarmVibrateUseCase
import com.maciel.murillo.mutodo.modules.settings.domain.usecase.SetUserNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getAlarmVibrateUseCase: GetAlarmVibrateUseCase,
    private val setAlarmVibrateUseCase: SetAlarmVibrateUseCase,
    private val getUserNameUseCase: GetUserNameUseCase,
    private val setUserNameUseCase: SetUserNameUseCase
) : ViewModel() {

    val vibrationEnabled = MutableLiveData<Boolean>()

    private val _goBack = MutableLiveData<Event<Unit>>()
    val goBack: LiveData<Event<Unit>> = _goBack

    private val _userName = MutableLiveData<String>().apply { value = "" }
    val userName: LiveData<String> = _userName

    private val _submitEnabled = MutableLiveData(false)
    val submitEnabled: LiveData<Boolean> = _submitEnabled

    fun onInitializeScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            vibrationEnabled.postValue(getAlarmVibrateUseCase.invoke())
            _userName.postValue(getUserNameUseCase.invoke())
        }
    }

    fun onBackClick() {
        _goBack.postValue(Event(Unit))
    }

    fun onChangeUserName(userName: String) {
        _userName.value = userName
        _submitEnabled.postValue(userName.isNotEmpty())
    }

    fun onSubmitClick() {
        viewModelScope.launch(Dispatchers.IO) {
            setUserNameUseCase.invoke(_userName.value.safe())
            setAlarmVibrateUseCase.invoke(vibrationEnabled.value.safe())
            _goBack.postValue(Event(Unit))
        }
    }
}