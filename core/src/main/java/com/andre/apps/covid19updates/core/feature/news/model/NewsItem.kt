package com.andre.apps.covid19updates.core.feature.news.model

import java.util.*

data class NewsItem(
    var headline: String,
    var date: Date,
    var subtitle: String?,
    var editorName: String?,
    var imageUrl: String?,
    var contentUrl: String,
    var source: String?
)