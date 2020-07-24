package com.andre.apps.covid19updates.core.feature.cases.repo

import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.core.feature.cases.model.CaseByCountry
import com.andre.apps.covid19updates.core.feature.cases.model.CaseType

interface ByCountryRemoteRepository {

    suspend fun getDataByCases(countrySlug: String, caseType: CaseType): Result<List<CaseByCountry>>
}