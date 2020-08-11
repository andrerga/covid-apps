package com.andre.apps.covid19updates.core.feature.summary.usecase

import com.andre.apps.covid19updates.core.feature.Usecase
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryLocalRepository
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryRemoteRepository
import com.andre.apps.covid19updates.core.util.DispatcherProvider
import javax.inject.Inject

class GetSummary @Inject constructor(
    private val remote: SummaryRemoteRepository,
    private val local: SummaryLocalRepository,
    dispatcherProvider: DispatcherProvider
) : Usecase(dispatcherProvider) {

    fun execute() =
        retrieveNetworkAndSync(
            { local.getSavedGlobalSummary() },
            { remote.getAllSummary() },
            { local.saveGlobalSummary(it) }
        )
}
