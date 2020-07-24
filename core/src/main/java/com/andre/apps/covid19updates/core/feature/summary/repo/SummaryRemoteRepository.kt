package com.andre.apps.covid19updates.core.feature.summary.repo

import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.core.feature.summary.model.Home

interface SummaryRemoteRepository {

    suspend fun getAllSummary(): Result<Home>
}