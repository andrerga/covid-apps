package com.andre.apps.covid19updates.ui.news

import androidx.paging.PageKeyedDataSource
import com.andre.apps.covid19updates.core.feature.news.model.NewsItem
import com.andre.apps.covid19updates.core.feature.news.repo.NewsRemoteRepository
import com.andre.apps.covid19updates.core.util.DefaultDispatcherProvider
import com.andre.apps.covid19updates.core.util.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NewsDataSource(
    private val repository: NewsRemoteRepository,
    private val scope: CoroutineScope,
    private val dispatcherProvider: DispatcherProvider =
        DefaultDispatcherProvider()
) : PageKeyedDataSource<Int, NewsItem>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, NewsItem>
    ) {
        scope.launch(dispatcherProvider.io()) {
            val res = repository.getCurrentNews(1)
            callback.onResult(res.data!!.items, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewsItem>) {
        scope.launch(dispatcherProvider.io()) {
            val res = repository.getCurrentNews(params.key)
            callback.onResult(res.data!!.items, params.key.inc())
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NewsItem>) {
        scope.launch(dispatcherProvider.io()) {
            val res = repository.getCurrentNews(params.key)
            callback.onResult(res.data!!.items, params.key.dec())
        }
    }
}