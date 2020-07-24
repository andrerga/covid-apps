package com.andre.apps.covid19updates.data.di.news

import com.andre.apps.covid19updates.core.feature.news.repo.NewsRemoteRepository
import com.andre.apps.covid19updates.data.base.ApiClient
import com.andre.apps.covid19updates.data.di.NetworkModule
import com.andre.apps.covid19updates.data.feature.news.api.NewsService
import com.andre.apps.covid19updates.data.feature.news.impl.NewsRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(includes = [NetworkModule::class])
class NewsDataModule {

    @Provides
    fun provideNewsService(apiClient: ApiClient): NewsService {
        return NewsService(apiClient)
    }

    @Provides
    @Reusable
    fun provideNewsRemoteRepository(service: NewsService): NewsRemoteRepository {
        return NewsRemoteRepositoryImpl(service)
    }
}