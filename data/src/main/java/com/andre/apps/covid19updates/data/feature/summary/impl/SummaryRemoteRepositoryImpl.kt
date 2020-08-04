package com.andre.apps.covid19updates.data.feature.summary.impl

import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.core.feature.summary.model.CountryItem
import com.andre.apps.covid19updates.core.feature.summary.model.Home
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryRemoteRepository
import com.andre.apps.covid19updates.core.util.parseToDate
import com.andre.apps.covid19updates.data.base.BaseImpl
import com.andre.apps.covid19updates.data.feature.summary.api.HomeService
import javax.inject.Inject

class SummaryRemoteRepositoryImpl @Inject constructor(
    private val service: HomeService
) : BaseImpl(), SummaryRemoteRepository {

    override suspend fun getAllSummary(): Result<Home> {

        return getResult(
            { service.getSummary() },
            {
                Home(
                    newConfirmed = it.global.newConfirmed,
                    totalConfirmed = it.global.totalConfirmed,
                    newRecovered = it.global.newRecovered,
                    totalRecovered = it.global.totalRecovered,
                    newDeaths = it.global.newDeaths,
                    totalDeaths = it.global.totalDeaths,
                    countries = it.countries.map { res ->
                        CountryItem(
                            countryName = res.countryName,
                            countrySlug = res.countrySlug,
                            newConfirmed = res.newConfirmed,
                            totalConfirmed = res.totalConfirmed,
                            newRecovered = res.newRecovered,
                            totalRecovered = res.totalRecovered,
                            newDeaths = res.newDeaths,
                            totalDeaths = res.totalDeaths,
                            lastUpdated = res.lastUpdated.parseToDate()
                        )
                    },
                    lastUpdated = it.lastUpdated.parseToDate()
                )
            }
        )
    }
}
