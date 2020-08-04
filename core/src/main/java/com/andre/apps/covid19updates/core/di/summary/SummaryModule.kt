package com.andre.apps.covid19updates.core.di.summary

import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryLocalRepository
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryRemoteRepository
import com.andre.apps.covid19updates.core.feature.summary.usecase.GetCountryInfo
import com.andre.apps.covid19updates.core.feature.summary.usecase.GetSummary
import dagger.Module
import dagger.Provides

@Module
class SummaryModule {

    @Provides
    @SummaryScope
    fun provideGetSummary(
        remote: SummaryRemoteRepository,
        local: SummaryLocalRepository
    ): GetSummary {
        return GetSummary(remote, local)
    }

    @Provides
    @SummaryScope
    fun provideGetCountryItems(
        local: SummaryLocalRepository
    ): GetCountryInfo {
        return GetCountryInfo(local)
    }
}
