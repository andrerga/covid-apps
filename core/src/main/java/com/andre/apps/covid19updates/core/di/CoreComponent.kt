package com.andre.apps.covid19updates.core.di

import com.andre.apps.covid19updates.core.di.cases.ByCountrySubcomponent
import com.andre.apps.covid19updates.core.di.news.NewsSubcomponent
import com.andre.apps.covid19updates.core.di.summary.SummarySubcomponent
import com.andre.apps.covid19updates.core.feature.cases.repo.ByCountryRemoteRepository
import com.andre.apps.covid19updates.core.feature.cases.usecase.GetCasesByCountry
import com.andre.apps.covid19updates.core.feature.news.repo.NewsRemoteRepository
import com.andre.apps.covid19updates.core.feature.news.usecase.GetNews
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryLocalRepository
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryRemoteRepository
import com.andre.apps.covid19updates.core.feature.summary.usecase.GetSummary
import dagger.BindsInstance
import dagger.Component

@Component
@CoreScope
interface CoreComponent {

    fun byCountryComponent(): ByCountrySubcomponent.Builder
    fun newsComponent(): NewsSubcomponent.Builder
    fun summaryComponent(): SummarySubcomponent.Builder

    @Component.Factory
    interface Factory {

        fun create(): CoreComponent
    }
}