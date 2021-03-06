package com.maciel.murillo.mutodo.modules.settings.domain.usecase

import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository

class SetUserNameUseCase(private val settingsRepository: SettingsRepository) {

    suspend operator fun invoke(userName: String) = settingsRepository.setUserName(userName)
}