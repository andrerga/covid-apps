package com.andre.apps.covid19updates.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.FragmentNavigator
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.andre.apps.covid19updates.core.feature.news.model.NewsItem
import com.andre.apps.covid19updates.core.feature.news.repo.NewsRemoteRepository
import com.andre.apps.covid19updates.core.util.DispatcherProvider
import com.andre.apps.covid19updates.nav.NavManager
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    private val navManager: NavManager,
    private val repository: NewsRemoteRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private lateinit var _news: LivePagedListBuilder<Int, NewsItem>
    val news get() = _news.build()

    fun initNews() {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()

        val factory = object : DataSource.Factory<Int, NewsItem>() {
            override fun create(): DataSource<Int, NewsItem> =
                NewsDataSource(repository, viewModelScope, dispatcherProvider)
        }

        _news = LivePagedListBuilder(
            factory,
            config
        ).setFetchExecutor(createFetchExecutor())
    }

    private fun createFetchExecutor(): ExecutorService {
        return Executors.newSingleThreadExecutor()
    }

    fun openWeb(
        url: String,
        transitionName: String,
        extras: FragmentNavigator.Extras
    ) {
        navManager.navigate(
            NewsFragmentDirections.actionNewsFragmentToWebViewFragment(
                url, transitionName
            ),
            extras
        )
    }
}
