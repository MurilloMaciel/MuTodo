package com.maciel.murillo.mutodo.modules.settings.domain.usecase

import android.net.Uri
import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository

class SetAlarmSoundUseCase(private val settingsRepository: SettingsRepository) {

    suspend operator fun invoke(soundUri: Uri) = settingsRepository.setAlarmSound(soundUri)
}