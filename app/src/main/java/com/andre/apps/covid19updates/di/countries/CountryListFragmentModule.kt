package com.andre.apps.covid19updates.di.countries

import com.andre.apps.covid19updates.ui.countries.CountryDetailFragment
import com.andre.apps.covid19updates.ui.countries.CountryListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CountryListFragmentModule {

    @ContributesAndroidInjector(modules = [CountryListViewModelModule::class])
    @CountriesListViewScope
    abstract fun inject(): CountryListFragment

    @ContributesAndroidInjector(modules = [CountryListViewModelModule::class])
    @CountriesListViewScope
    abstract fun injectDetail(): CountryDetailFragment
}
