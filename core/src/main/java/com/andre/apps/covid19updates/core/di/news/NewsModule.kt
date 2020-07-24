package com.andre.apps.covid19updates.core.di.news

import com.andre.apps.covid19updates.core.feature.news.repo.NewsRemoteRepository
import com.andre.apps.covid19updates.core.feature.news.usecase.GetNews
import dagger.Module
import dagger.Provides

@Module
class NewsModule {

    @Provides
    @NewsScope
    fun provideGetNews(remote: NewsRemoteRepository): GetNews {
        return GetNews(remote)
    }
}