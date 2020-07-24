package com.andre.apps.covid19updates.core.cases

import com.andre.apps.covid19updates.core.di.cases.ByCountrySubcomponent
import com.andre.apps.covid19updates.core.feature.cases.repo.ByCountryRemoteRepository
import com.andre.apps.covid19updates.core.feature.cases.usecase.GetCasesByCountry
import dagger.BindsInstance
import dagger.Component

@Component
@ByCountryTestScope
interface ByCountryTestComponent {

    fun byCountryComponent(): ByCountrySubcomponent.Builder
    fun getCasesByCountry(): GetCasesByCountry

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance remote: ByCountryRemoteRepository): ByCountryTestComponent
    }
}