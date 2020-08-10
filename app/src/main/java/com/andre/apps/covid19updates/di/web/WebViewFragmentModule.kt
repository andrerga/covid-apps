package com.andre.apps.covid19updates.di.web

import com.andre.apps.covid19updates.ui.web.WebViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WebViewFragmentModule {

    @ContributesAndroidInjector
    @WebViewScope
    abstract fun inject(): WebViewFragment
}
