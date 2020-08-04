package com.andre.apps.covid19updates.di

import com.andre.apps.covid19updates.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun injectMainActivity(): MainActivity
}