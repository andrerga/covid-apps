package com.andre.apps.covid19updates.data.di.summary

import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryLocalRepository
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryRemoteRepository
import com.andre.apps.covid19updates.data.base.ApiClient
import com.andre.apps.covid19updates.data.base.LocalDb
import com.andre.apps.covid19updates.data.di.LocalModule
import com.andre.apps.covid19updates.data.di.NetworkModule
import com.andre.apps.covid19updates.data.feature.summary.api.HomeService
import com.andre.apps.covid19updates.data.feature.summary.db.HomeDbService
import com.andre.apps.covid19updates.data.feature.summary.impl.SummaryLocalRepositoryImpl
import com.andre.apps.covid19updates.data.feature.summary.impl.SummaryRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(includes = [NetworkModule::class, LocalModule::class])
class SummaryDataModule {

    @Provides
    fun provideHomeService(apiClient: ApiClient): HomeService {
        return HomeService(apiClient)
    }

    @Provides
    fun provideSummaryDbService(localDb: LocalDb): HomeDbService {
        return HomeDbService(localDb)
    }

    @Provides
    @Reusable
    fun provideSummaryRemoteRepository(service: HomeService): SummaryRemoteRepository {
        return SummaryRemoteRepositoryImpl(service)
    }

    @Provides
    @Reusable
    fun provideSummaryLocalRepository(dbService: HomeDbService): SummaryLocalRepository {
        return SummaryLocalRepositoryImpl(dbService)
    }
}
