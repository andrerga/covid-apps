package com.andre.apps.covid19updates.data.feature.summary.impl

import com.andre.apps.covid19updates.core.feature.summary.model.CountryItem
import com.andre.apps.covid19updates.core.feature.summary.model.Home
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryLocalRepository
import com.andre.apps.covid19updates.data.feature.summary.db.HomeDbService
import com.andre.apps.covid19updates.data.feature.summary.entity.CountryItemEntity
import com.andre.apps.covid19updates.data.feature.summary.entity.HomeEntity
import javax.inject.Inject

class SummaryLocalRepositoryImpl @Inject constructor(
    private val homeDbService: HomeDbService
) : SummaryLocalRepository {

    override suspend fun getSavedGlobalSummary(): Home? {

        val entity = homeDbService.getSavedGlobalSummary()

        return if (entity == null) {
            null
        } else {
            Home(
                newConfirmed = entity.newConfirmed,
                totalConfirmed = entity.totalConfirmed,
                newRecovered = entity.newRecovered,
                totalRecovered = entity.totalRecovered,
                newDeaths = entity.newDeaths,
                totalDeaths = entity.totalDeaths,
                countries = entity.countries.map {
                    CountryItem(
                        countryName = it.countryName,
                        countrySlug = it.countrySlug,
                        newConfirmed = it.newConfirmed,
                        totalConfirmed = it.totalConfirmed,
                        newRecovered = it.newRecovered,
                        totalRecovered = it.totalRecovered,
                        newDeaths = it.newDeaths,
                        totalDeaths = it.totalDeaths,
                        lastUpdated = it.lastUpdated
                    )
                },
                lastUpdated = entity.lastRefreshed
            )
        }
    }

    override suspend fun saveGlobalSummary(summary: Home) {
        val newEntity = HomeEntity(
            newConfirmed = summary.newConfirmed,
            totalConfirmed = summary.totalConfirmed,
            newRecovered = summary.newRecovered,
            totalRecovered = summary.totalRecovered,
            newDeaths = summary.newDeaths,
            totalDeaths = summary.totalDeaths,
            lastRefreshed = summary.lastUpdated
        )
        for (item in summary.countries) {
            val itemEntity = CountryItemEntity(
                countryName = item.countryName,
                countrySlug = item.countrySlug,
                newConfirmed = item.newConfirmed,
                totalConfirmed = item.totalConfirmed,
                newDeaths = item.newDeaths,
                totalDeaths = item.totalDeaths,
                newRecovered = item.newRecovered,
                totalRecovered = item.totalRecovered,
                lastUpdated = item.lastUpdated
            )

            newEntity.countries.add(itemEntity)
        }

        homeDbService.saveGlobalSummary(newEntity)
    }

    override suspend fun getCountrySummary(): List<CountryItem>? {

        return homeDbService.getCountrySummary()?.map {
            CountryItem(
                countryName = it.countryName,
                countrySlug = it.countrySlug,
                newConfirmed = it.newConfirmed,
                newDeaths = it.newDeaths,
                newRecovered = it.newRecovered,
                totalConfirmed = it.totalConfirmed,
                totalDeaths = it.totalDeaths,
                totalRecovered = it.totalRecovered,
                lastUpdated = it.lastUpdated
            )
        }
    }
}
