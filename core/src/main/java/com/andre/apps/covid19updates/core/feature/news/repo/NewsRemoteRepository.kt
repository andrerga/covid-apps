package com.andre.apps.covid19updates.core.feature.news.repo

import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.core.feature.news.model.News
import com.andre.apps.covid19updates.core.feature.news.model.NewsItem
import com.andre.apps.covid19updates.core.feature.summary.model.CountryItem

interface NewsRemoteRepository {

    suspend fun getCurrentNews(page: Int = 1): Result<News>
}