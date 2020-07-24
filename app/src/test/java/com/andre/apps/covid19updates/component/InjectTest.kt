package com.andre.apps.covid19updates.component

import com.andre.apps.covid19updates.core.feature.news.usecase.GetNews
import javax.inject.Inject

class InjectTest @Inject constructor(private val getNews: GetNews) {

    fun news(): GetNews = getNews
}