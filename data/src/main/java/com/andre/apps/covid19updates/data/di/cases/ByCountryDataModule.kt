package com.andre.apps.covid19updates.data.di.cases

import com.andre.apps.covid19updates.core.feature.cases.repo.ByCountryRemoteRepository
import com.andre.apps.covid19updates.data.base.ApiClient
import com.andre.apps.covid19updates.data.di.NetworkModule
import com.andre.apps.covid19updates.data.feature.cases.api.ByCountryService
import com.andre.apps.covid19updates.data.feature.cases.impl.ByCountryRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(includes = [NetworkModule::class])
class ByCountryDataModule {

    @Provides
    fun provideByCountryService(apiClient: ApiClient): ByCountryService {
        return ByCountryService(apiClient)
    }

    @Provides
    @Reusable
    fun provideByCountryRemoteRepository(service: ByCountryService): ByCountryRemoteRepository {
        return ByCountryRemoteRepositoryImpl(service)
    }
}