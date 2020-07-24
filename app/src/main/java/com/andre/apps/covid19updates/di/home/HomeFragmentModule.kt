package com.andre.apps.covid19updates.di.home

import com.andre.apps.covid19updates.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentModule {

    @ContributesAndroidInjector(modules = [HomeViewModelModule::class])
    @HomeViewScope
    abstract fun inject(): HomeFragment
}