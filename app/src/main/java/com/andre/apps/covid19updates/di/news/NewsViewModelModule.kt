package com.andre.apps.covid19updates.di.news

import androidx.lifecycle.ViewModel
import com.andre.apps.covid19updates.di.vmf.ViewModelKey
import com.andre.apps.covid19updates.di.vmf.ViewModelModule
import com.andre.apps.covid19updates.ui.news.NewsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
abstract class NewsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(newsViewModel: NewsViewModel): ViewModel
}