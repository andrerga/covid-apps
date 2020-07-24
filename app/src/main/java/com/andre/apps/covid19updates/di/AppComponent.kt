package com.andre.apps.covid19updates.di

import android.app.Application
import com.andre.apps.covid19updates.Covid19Application
import com.andre.apps.covid19updates.core.di.CoreComponent
import com.andre.apps.covid19updates.core.util.DispatcherProvider
import com.andre.apps.covid19updates.data.di.DataComponent
import com.andre.apps.covid19updates.di.countries.CountryListFragmentModule
import com.andre.apps.covid19updates.di.home.HomeFragmentModule
import com.andre.apps.covid19updates.di.news.NewsFragmentModule
import com.andre.apps.covid19updates.di.web.WebViewFragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class,
    AppModule::class,
    MainActivityModule::class,
    HomeFragmentModule::class,
    CountryListFragmentModule::class,
    NewsFragmentModule::class,
    WebViewFragmentModule::class
], dependencies = [
    DataComponent::class,
    CoreComponent::class])
@AppScope
interface AppComponent : AndroidInjector<Covid19Application> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun dispatcherProvider(dispatcherProvider: DispatcherProvider): Builder

        fun dataComponent(dataComponent: DataComponent): Builder
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun build(): AppComponent
    }
}