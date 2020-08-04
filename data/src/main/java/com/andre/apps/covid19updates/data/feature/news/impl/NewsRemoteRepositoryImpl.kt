package com.andre.apps.covid19updates.data.feature.news.impl

import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.core.feature.news.model.News
import com.andre.apps.covid19updates.core.feature.news.model.NewsItem
import com.andre.apps.covid19updates.core.feature.news.repo.NewsRemoteRepository
import com.andre.apps.covid19updates.core.util.parseToDate
import com.andre.apps.covid19updates.data.base.BaseImpl
import com.andre.apps.covid19updates.data.feature.news.api.NewsService
import javax.inject.Inject

class NewsRemoteRepositoryImpl @Inject constructor(
    private val service: NewsService
) : BaseImpl(), NewsRemoteRepository {

    override suspend fun getCurrentNews(page: Int): Result<News> =
        getResult(
            { service.getAllNewsByCountry("us", page) },
            { data ->
                if (data.status != "ok")
                    throw Exception("Error")

                News(
                    total = data.totalResults,
                    items = data.articles.map {
                        NewsItem(
                            headline = it.title,
                            date = it.publishedAt.parseToDate(),
                            subtitle = it.description,
                            editorName = it.author,
                            imageUrl = it.urlToImage,
                            contentUrl = it.url,
                            source = it.sourceMap["name"]
                        )
                    }
                )
            }
        )
}
