package com.andre.apps.covid19updates.data.feature.news.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @Json(name = "status")
    var status: String,
    @Json(name = "totalResults")
    var totalResults: Long,
    @Json(name = "articles")
    var articles: List<ArticleResponse>
)