package com.andre.apps.covid19updates.core.di.cases

import dagger.Subcomponent

@Subcomponent(modules = [ByCountryModule::class])
@ByCountryScope
interface ByCountrySubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): ByCountrySubcomponent
    }
}