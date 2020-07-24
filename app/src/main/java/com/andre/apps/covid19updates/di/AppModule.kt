package com.andre.apps.covid19updates.di

import android.app.Application
import android.content.Context
import com.andre.apps.covid19updates.nav.NavManager
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Module
class AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @AppScope
    fun provideNavManager(): NavManager {
        return NavManager()
    }
}