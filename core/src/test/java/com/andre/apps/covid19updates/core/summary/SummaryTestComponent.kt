package com.andre.apps.covid19updates.core.summary

import com.andre.apps.covid19updates.core.di.summary.SummarySubcomponent
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryLocalRepository
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryRemoteRepository
import com.andre.apps.covid19updates.core.feature.summary.usecase.GetSummary
import dagger.BindsInstance
import dagger.Component

@Component
@SummaryTestScope
interface SummaryTestComponent {

    fun summaryComponent(): SummarySubcomponent.Builder
    fun getSummary(): GetSummary

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance remote: SummaryRemoteRepository, @BindsInstance local: SummaryLocalRepository): SummaryTestComponent
    }
}