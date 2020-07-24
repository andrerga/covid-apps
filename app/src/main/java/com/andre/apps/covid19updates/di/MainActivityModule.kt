package com.andre.apps.covid19updates.di

import com.andre.apps.covid19updates.ui.MainActivity
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun injectMainActivity(): MainActivity
}