package com.andre.apps.covid19updates.component

import com.andre.apps.covid19updates.core.di.CoreComponent
import com.andre.apps.covid19updates.core.feature.cases.repo.ByCountryRemoteRepository
import com.andre.apps.covid19updates.core.feature.news.repo.NewsRemoteRepository
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryLocalRepository
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryRemoteRepository
import com.andre.apps.covid19updates.core.util.DispatcherProvider
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppTestModule::class], dependencies = [CoreComponent::class])
@AppTestScope
interface AppTestComponent {

    fun getInjectTest(): InjectTest

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun byCountryRemoteRepository(byCountryRemoteRepository: ByCountryRemoteRepository): Builder

        @BindsInstance
        fun newsRemoteRepository(newsRemoteRepository: NewsRemoteRepository): Builder

        @BindsInstance
        fun summaryRemoteRepository(summaryRemoteRepository: SummaryRemoteRepository): Builder

        @BindsInstance
        fun summaryLocalRepository(summaryLocalRepository: SummaryLocalRepository): Builder

        @BindsInstance
        fun dispatcherProvider(dispatcherProvider: DispatcherProvider): Builder

        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): AppTestComponent
    }
}
