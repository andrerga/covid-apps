package com.andre.apps.covid19updates.di.news

import com.andre.apps.covid19updates.ui.news.NewsFragment
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class])
abstract class NewsFragmentModule {

    @ContributesAndroidInjector(modules = [NewsViewModelModule::class])
    @NewsViewScope
    abstract fun inject(): NewsFragment
}