package com.andre.apps.covid19updates.data.feature.cases.impl

import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.core.feature.cases.model.CaseByCountry
import com.andre.apps.covid19updates.core.feature.cases.model.CaseType
import com.andre.apps.covid19updates.core.feature.cases.repo.ByCountryRemoteRepository
import com.andre.apps.covid19updates.core.util.parseToDate
import com.andre.apps.covid19updates.data.base.BaseImpl
import com.andre.apps.covid19updates.data.feature.cases.api.ByCountryService
import javax.inject.Inject

class ByCountryRemoteRepositoryImpl @Inject constructor(private val service: ByCountryService) : BaseImpl(), ByCountryRemoteRepository {

    override suspend fun getDataByCases(countrySlug: String, caseType: CaseType): Result<List<CaseByCountry>> {
        return getResult(
            { service.getCasesByCountry(countrySlug, caseType.string) },
            {
                it
                    .map { res ->
                        CaseByCountry(
                            cases = res.cases,
                            date = res.date.parseToDate()
                        )
                    }
            }
        )
    }
}