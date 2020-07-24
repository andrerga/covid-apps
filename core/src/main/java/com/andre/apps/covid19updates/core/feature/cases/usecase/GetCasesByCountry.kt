package com.andre.apps.covid19updates.core.feature.cases.usecase

import com.andre.apps.covid19updates.core.feature.Usecase
import com.andre.apps.covid19updates.core.feature.cases.model.CaseType
import com.andre.apps.covid19updates.core.feature.cases.repo.ByCountryRemoteRepository
import com.andre.apps.covid19updates.core.util.DispatcherProvider
import javax.inject.Inject

class GetCasesByCountry @Inject constructor(private val remote: ByCountryRemoteRepository) : Usecase() {

    fun execute(slug: String, caseType: CaseType, dispatcherProvider: DispatcherProvider) =
        retrieveNetwork(
            { remote.getDataByCases(slug, caseType) }, dispatcherProvider)
}