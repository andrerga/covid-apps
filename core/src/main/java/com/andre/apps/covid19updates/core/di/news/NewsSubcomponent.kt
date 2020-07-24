package com.andre.apps.covid19updates.core.di.news

import dagger.Subcomponent

@Subcomponent(modules = [NewsModule::class])
@NewsScope
interface NewsSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): NewsSubcomponent
    }
}