package com.andre.apps.covid19updates.core.feature.summary.usecase

import com.andre.apps.covid19updates.core.feature.Usecase
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryLocalRepository
import javax.inject.Inject

class GetCountryInfo @Inject constructor(private val repository: SummaryLocalRepository) : Usecase() {

    fun execute() = retrieveLocal(
        repository::getCountrySummary
    )
}