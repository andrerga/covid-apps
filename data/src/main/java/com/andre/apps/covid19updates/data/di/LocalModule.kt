package com.andre.apps.covid19updates.data.di

import android.app.Application
import com.andre.apps.covid19updates.data.base.LocalDb
import com.andre.apps.covid19updates.data.feature.summary.entity.MyObjectBox
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.objectbox.BoxStore

@Module
class LocalModule {

    @Provides
    @Reusable
    fun provideBoxStore(application: Application): BoxStore {
        return MyObjectBox.builder()
            .androidContext(application.applicationContext)
            .build()
    }

    @Provides
    @Reusable
    fun provideLocalDatabase(boxStore: BoxStore): LocalDb {
        return LocalDb(boxStore)
    }
}
