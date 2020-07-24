package com.andre.apps.covid19updates.core.feature.summary.repo

import com.andre.apps.covid19updates.core.feature.summary.model.CountryItem
import com.andre.apps.covid19updates.core.feature.summary.model.Home

interface SummaryLocalRepository {

    fun getSavedGlobalSummary(): Home?
    fun getCountrySummary(): List<CountryItem>?
    suspend fun saveGlobalSummary(summary: Home)
}