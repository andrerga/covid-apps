package com.andre.apps.covid19updates.di.home

import androidx.lifecycle.ViewModel
import com.andre.apps.covid19updates.di.vmf.ViewModelKey
import com.andre.apps.covid19updates.di.vmf.ViewModelModule
import com.andre.apps.covid19updates.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class HomeViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindViewModel(viewModel: HomeViewModel): ViewModel
}
