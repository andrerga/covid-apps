package com.andre.apps.covid19updates.di.countries

import com.andre.apps.covid19updates.ui.countries.CountryListFragment
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class])
abstract class CountryListFragmentModule {

    @ContributesAndroidInjector(modules = [CountryListViewModelModule::class])
    @CountriesListViewScope
    abstract fun inject(): CountryListFragment
}