package com.andre.apps.covid19updates.component

import com.andre.apps.covid19updates.core.feature.news.usecase.GetNews
import dagger.Module
import dagger.Provides

@Module
class AppTestModule {

    @Provides
    @AppTestScope
    fun provideInjectTest(getNews: GetNews): InjectTest {
        return InjectTest(getNews)
    }
}