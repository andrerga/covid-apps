package com.andre.apps.covid19updates.data.di

import android.app.Application
import com.andre.apps.covid19updates.core.feature.cases.repo.ByCountryRemoteRepository
import com.andre.apps.covid19updates.core.feature.news.repo.NewsRemoteRepository
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryLocalRepository
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryRemoteRepository
import com.andre.apps.covid19updates.data.di.cases.ByCountryDataModule
import com.andre.apps.covid19updates.data.di.news.NewsDataModule
import com.andre.apps.covid19updates.data.di.summary.SummaryDataModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ByCountryDataModule::class, SummaryDataModule::class, NewsDataModule::class])
@DataScope
interface DataComponent {

    fun summaryRemoteRepository(): SummaryRemoteRepository
    fun summaryLocalRepository(): SummaryLocalRepository
    fun newsRemoteRepository(): NewsRemoteRepository
    fun byCountryRemoteRepository(): ByCountryRemoteRepository

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): DataComponent
    }
}
