package com.andre.apps.covid19updates.core.feature.news.usecase

import com.andre.apps.covid19updates.core.feature.Usecase
import com.andre.apps.covid19updates.core.feature.news.repo.NewsRemoteRepository
import com.andre.apps.covid19updates.core.util.DefaultDispatcherProvider
import com.andre.apps.covid19updates.core.util.DispatcherProvider
import javax.inject.Inject

class GetNews @Inject constructor(private val remote: NewsRemoteRepository) : Usecase() {

    fun execute(page: Int = 1, dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()) =
        retrieveNetwork({ remote.getCurrentNews(page) }, dispatcherProvider)
}