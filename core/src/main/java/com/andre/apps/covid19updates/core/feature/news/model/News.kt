package com.andre.apps.covid19updates.core.feature.news.model

data class News(
    var total: Long,
    var items: List<NewsItem>
)