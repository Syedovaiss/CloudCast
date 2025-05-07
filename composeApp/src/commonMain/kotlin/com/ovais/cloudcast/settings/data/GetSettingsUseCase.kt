package com.ovais.cloudcast.settings.data

import com.ovais.cloudcast.core.data.database.AppSettings
import com.ovais.cloudcast.core.domain.usecase.SuspendUseCase

interface GetSettingsUseCase : SuspendUseCase<AppSettings>
class DefaultGetSettingsUseCase(
    private val repository: SettingsRepository
) : GetSettingsUseCase {
    override suspend fun invoke(): AppSettings {
        TODO("Not yet implemented")
    }
}