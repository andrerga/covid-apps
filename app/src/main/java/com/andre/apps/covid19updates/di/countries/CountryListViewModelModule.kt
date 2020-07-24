package com.andre.apps.covid19updates.di.countries

import androidx.lifecycle.ViewModel
import com.andre.apps.covid19updates.di.vmf.ViewModelKey
import com.andre.apps.covid19updates.di.vmf.ViewModelModule
import com.andre.apps.covid19updates.ui.countries.CountryListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class CountryListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CountryListViewModel::class)
    abstract fun bindCountryListViewModel(countryListViewModel: CountryListViewModel): ViewModel
}