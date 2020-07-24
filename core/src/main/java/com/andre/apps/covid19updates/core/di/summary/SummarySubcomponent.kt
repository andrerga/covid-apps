package com.andre.apps.covid19updates.core.di.summary

import dagger.Subcomponent

@Subcomponent(modules = [SummaryModule::class])
@SummaryScope
interface SummarySubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): SummarySubcomponent
    }
}