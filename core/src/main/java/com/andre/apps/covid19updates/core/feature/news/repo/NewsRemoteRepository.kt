package com.andre.apps.covid19updates.core.feature.news.repo

import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.core.feature.news.model.News

interface NewsRemoteRepository {

    suspend fun getCurrentNews(page: Int = 1): Result<News>
}