package com.maciel.murillo.mutodo.modules.settings.domain.usecase

import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository
import com.maciel.murillo.mutodo.modules.tasks.domain.usecase.alarm.ScheduleAlarmUpdateUseCase

class SetInitialSettingsUseCase(private val repository: SettingsRepository,
                                private val scheduleAlarmUpdatesUseCase: ScheduleAlarmUpdateUseCase) {

    suspend operator fun invoke() = with(repository) {
        if (isFirstTimeLaunchingApp().not()) {
            return
        }

        setFirstTimeLaunchingApp(false)
        setAlarmVibrate(true)
        setDefaultAlarmTone()
        scheduleAlarmUpdatesUseCase.invoke()
    }
}