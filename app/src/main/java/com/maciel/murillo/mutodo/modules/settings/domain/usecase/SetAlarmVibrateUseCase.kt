package com.maciel.murillo.mutodo.modules.settings.domain.usecase

import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository

class SetAlarmVibrateUseCase(private val settingsRepository: SettingsRepository) {

    suspend operator fun invoke(vibrate: Boolean) = settingsRepository.setAlarmVibrate(vibrate)
}