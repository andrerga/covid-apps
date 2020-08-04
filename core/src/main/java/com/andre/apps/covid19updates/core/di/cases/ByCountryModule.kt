package com.andre.apps.covid19updates.core.di.cases

import com.andre.apps.covid19updates.core.feature.cases.repo.ByCountryRemoteRepository
import com.andre.apps.covid19updates.core.feature.cases.usecase.GetCasesByCountry
import dagger.Module
import dagger.Provides

@Module
class ByCountryModule {

    @Provides
    @ByCountryScope
    fun provideGetCasesByCountry(remote: ByCountryRemoteRepository): GetCasesByCountry {
        return GetCasesByCountry(remote)
    }
}
