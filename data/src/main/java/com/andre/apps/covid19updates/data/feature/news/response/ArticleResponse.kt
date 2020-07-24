package com.andre.apps.covid19updates.data.feature.news.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleResponse(
    @Json(name = "source")
    var sourceMap: Map<String, String?>,
    @Json(name = "author")
    var author: String?,
    @Json(name = "title")
    var title: String,
    @Json(name = "description")
    var description: String?,
    @Json(name = "url")
    var url: String,
    @Json(name = "urlToImage")
    var urlToImage: String?,
    @Json(name = "publishedAt")
    var publishedAt: String
)