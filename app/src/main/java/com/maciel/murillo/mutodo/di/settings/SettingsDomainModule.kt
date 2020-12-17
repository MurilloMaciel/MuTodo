package com.maciel.murillo.mutodo.di.settings

import com.maciel.murillo.mutodo.modules.settings.domain.usecase.*
import org.koin.dsl.module

val settingsDomainModule = module {
    factory { GetAlarmSoundUseCase(get()) }
    factory { GetAlarmVibrateUseCase(get()) }
    factory { SetAlarmSoundUseCase(get()) }
    factory { SetAlarmVibrateUseCase(get()) }
    factory { SetInitialSettingsUseCase(get(), get()) }
}