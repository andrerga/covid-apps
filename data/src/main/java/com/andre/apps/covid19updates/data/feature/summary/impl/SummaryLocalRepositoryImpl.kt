package com.andre.apps.covid19updates.data.feature.summary.impl

import com.andre.apps.covid19updates.core.feature.summary.model.CountryItem
import com.andre.apps.covid19updates.core.feature.summary.model.Home
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryLocalRepository
import com.andre.apps.covid19updates.data.base.ObjectBox
import com.andre.apps.covid19updates.data.feature.summary.entity.CountryItemEntity
import com.andre.apps.covid19updates.data.feature.summary.entity.CountryItemEntity_
import com.andre.apps.covid19updates.data.feature.summary.entity.HomeEntity
import com.andre.apps.covid19updates.data.feature.summary.entity.HomeEntity_
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SummaryLocalRepositoryImpl : SummaryLocalRepository {

    override fun getSavedGlobalSummary(): Home? {
        val boxStore = ObjectBox.boxStore

        val box: Box<HomeEntity> = boxStore.boxFor()
        val entity = box.query().build().findFirst()
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
        val boxStore = ObjectBox.boxStore
        return suspendCoroutine { continuation ->
            boxStore.runInTxAsync({
                val box: Box<HomeEntity> = boxStore.boxFor()
                val entityQuery = box.query()
                val entity = entityQuery.build().findFirst()
                if (entity == null) {
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

                    box.put(newEntity)
                } else {
                    val countriesBox: Box<CountryItemEntity> = boxStore.boxFor()
                    countriesBox.removeAll()

                    entity.newConfirmed = summary.newConfirmed
                    entity.totalConfirmed = summary.totalConfirmed
                    entity.newRecovered = summary.newRecovered
                    entity.totalRecovered = summary.totalRecovered
                    entity.newDeaths = summary.newDeaths
                    entity.totalDeaths = summary.totalDeaths
                    entity.lastRefreshed = summary.lastUpdated

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

                        entity.countries.add(itemEntity)
                    }

                    box.put(entity)
                }
            }, { _, error ->
                if (error != null) {
                    continuation.resumeWithException(error)
                } else {
                    continuation.resume(Unit)
                }
            })
        }
    }

    override fun getCountrySummary(): List<CountryItem>? {
        val boxStore = ObjectBox.boxStore

        val box: Box<HomeEntity> = boxStore.boxFor()
        val builder = box.query()
        val entity = builder.build().findFirst()
        val countries = entity?.countries?.toList() ?: return null
        return countries.map {
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