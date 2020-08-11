package com.andre.apps.covid19updates.core.di

import com.andre.apps.covid19updates.core.di.cases.ByCountrySubcomponent
import com.andre.apps.covid19updates.core.di.news.NewsSubcomponent
import com.andre.apps.covid19updates.core.di.summary.SummarySubcomponent
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
