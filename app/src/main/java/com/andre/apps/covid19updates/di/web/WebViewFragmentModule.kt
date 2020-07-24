package com.andre.apps.covid19updates.di.web

import com.andre.apps.covid19updates.ui.web.WebViewFragment
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class])
abstract class WebViewFragmentModule {

    @ContributesAndroidInjector
    @WebViewScope
    abstract fun inject(): WebViewFragment
}