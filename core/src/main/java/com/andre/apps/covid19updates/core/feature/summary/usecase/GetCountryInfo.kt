package com.andre.apps.covid19updates.core.feature.summary.usecase

import com.andre.apps.covid19updates.core.feature.Usecase
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryLocalRepository
import com.andre.apps.covid19updates.core.util.DispatcherProvider
import javax.inject.Inject

class GetCountryInfo @Inject constructor(
    private val repository: SummaryLocalRepository,
    dispatcherProvider: DispatcherProvider
) : Usecase(dispatcherProvider) {

    fun execute() =
        retrieveLocal(
            repository::getCountrySummary
        )
}
