package com.andre.apps.covid19updates.core.news

import com.andre.apps.covid19updates.core.di.news.NewsSubcomponent
import com.andre.apps.covid19updates.core.feature.news.repo.NewsRemoteRepository
import com.andre.apps.covid19updates.core.feature.news.usecase.GetNews
import com.andre.apps.covid19updates.core.util.DispatcherProvider
import dagger.BindsInstance
import dagger.Component

@Component
@NewsTestScope
interface NewsTestComponent {

    fun newsComponent(): NewsSubcomponent.Builder
    fun getNews(): GetNews

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance remoteRepository: NewsRemoteRepository, @BindsInstance dispatcherProvider: DispatcherProvider): NewsTestComponent
    }
}
