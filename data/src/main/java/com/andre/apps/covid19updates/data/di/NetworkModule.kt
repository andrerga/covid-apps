package com.andre.apps.covid19updates.data.di

import android.app.Application
import android.content.Context
import com.andre.apps.covid19updates.data.base.ApiClient
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun provideDispatcher(): Dispatcher {
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 10
        return dispatcher
    }

    @Provides
    @Reusable
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    @Provides
    @Reusable
    fun provideOkHttpClient(dispatcher: Dispatcher): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .addInterceptor { chain ->
                var request = chain.request()
                request = request.newBuilder()
                    .addHeader("X-Auth-Token", "e8c9f3d21aa44ccaa8c3d3ae52ef749e")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Reusable
    fun provideApiService(client: OkHttpClient, moshi: Moshi): ApiClient {
        return ApiClient(client, moshi)
    }
}