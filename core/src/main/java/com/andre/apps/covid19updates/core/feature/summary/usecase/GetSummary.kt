package com.andre.apps.covid19updates.core.feature.summary.usecase

import com.andre.apps.covid19updates.core.feature.Usecase
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryLocalRepository
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryRemoteRepository
import com.andre.apps.covid19updates.core.util.DefaultDispatcherProvider
import com.andre.apps.covid19updates.core.util.DispatcherProvider
import javax.inject.Inject

class GetSummary @Inject constructor(
    private val remote: SummaryRemoteRepository,
    private val local: SummaryLocalRepository
) : Usecase() {

    fun execute(
        dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()
    ) =
        retrieveNetworkAndSync(
            { local.getSavedGlobalSummary() },
            { remote.getAllSummary() },
            { local.saveGlobalSummary(it) },
            dispatcherProvider)
}
