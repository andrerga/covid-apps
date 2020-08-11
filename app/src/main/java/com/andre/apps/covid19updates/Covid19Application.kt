package com.andre.apps.covid19updates

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.andre.apps.covid19updates.core.di.DaggerCoreComponent
import com.andre.apps.covid19updates.core.util.DefaultDispatcherProvider
import com.andre.apps.covid19updates.data.base.ObjectBox
import com.andre.apps.covid19updates.data.di.DaggerDataComponent
import com.andre.apps.covid19updates.di.DaggerAppComponent
import com.andre.apps.covid19updates.di.Injectable
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjection

class Covid19Application : DaggerApplication(), Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()

        ObjectBox.init(this)
        registerActivityLifecycleCallbacks(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .dispatcherProvider(DefaultDispatcherProvider())
            .dataComponent(DaggerDataComponent
                .factory()
                .create(this))
            .coreComponent(DaggerCoreComponent
                .factory()
                .create())
            .build()
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        handleActivity(activity)
    }

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {}

    private fun handleActivity(activity: Activity) {
        AndroidInjection.inject(activity)

        if (activity is FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentCreated(
                        fm: FragmentManager,
                        f: Fragment,
                        savedInstanceState: Bundle?
                    ) {
                        super.onFragmentCreated(fm, f, savedInstanceState)

                        if (f is Injectable) {
                            AndroidSupportInjection.inject(f)
                        }
                    }
                }, true)
        }
    }

    companion object {

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }
}
