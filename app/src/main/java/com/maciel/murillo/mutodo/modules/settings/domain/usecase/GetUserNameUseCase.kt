package com.maciel.murillo.mutodo.modules.settings.domain.usecase

import com.maciel.murillo.mutodo.modules.settings.domain.repository.SettingsRepository

class GetUserNameUseCase(private val settingsRepository: SettingsRepository) {

    suspend operator fun invoke() = settingsRepository.getUserName()
}