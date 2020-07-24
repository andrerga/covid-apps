package com.andre.apps.covid19updates.data.feature.news.api

import com.andre.apps.covid19updates.data.base.ApiClient
import com.andre.apps.covid19updates.data.base.ApiClient.Builder.Companion.get
import com.andre.apps.covid19updates.data.base.UrlType
import com.andre.apps.covid19updates.data.feature.news.response.NewsResponse
import javax.inject.Inject

class NewsService @Inject constructor(private val api: ApiClient) {

    suspend fun getAllNewsByCountry(country: String, page: Int = 1): NewsResponse {
        return api.call("top-headlines", UrlType.News)
            .get {
                addQueryParam("category", "health")
                addQueryParam("page", page.toString())
                addQueryParam("pageSize", "20")
                addQueryParam("country", country)
            }
    }
}