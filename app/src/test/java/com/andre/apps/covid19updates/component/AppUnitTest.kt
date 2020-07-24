package com.andre.apps.covid19updates.component

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.andre.apps.covid19updates.CoroutinesTestRule
import com.andre.apps.covid19updates.core.di.DaggerCoreComponent
import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.core.feature.cases.repo.ByCountryRemoteRepository
import com.andre.apps.covid19updates.core.feature.news.model.News
import com.andre.apps.covid19updates.core.feature.news.model.NewsItem
import com.andre.apps.covid19updates.core.feature.news.repo.NewsRemoteRepository
import com.andre.apps.covid19updates.core.feature.news.usecase.GetNews
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryLocalRepository
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryRemoteRepository
import com.andre.apps.covid19updates.core.util.parseToDate
import com.andre.apps.covid19updates.getOrAwaitValue
import com.andre.apps.covid19updates.runBlockingTest
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class AppUnitTest {

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    @Mock
    lateinit var byCountryRemoteRepository: ByCountryRemoteRepository

    @Mock
    lateinit var newsRemoteRepository: NewsRemoteRepository

    @Mock
    lateinit var summaryRemoteRepository: SummaryRemoteRepository

    @Mock
    lateinit var summaryLocalRepository: SummaryLocalRepository

    private lateinit var getNews: GetNews

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        getNews = DaggerAppTestComponent
            .builder()
            .byCountryRemoteRepository(byCountryRemoteRepository)
            .newsRemoteRepository(newsRemoteRepository)
            .summaryLocalRepository(summaryLocalRepository)
            .summaryRemoteRepository(summaryRemoteRepository)
            .dispatcherProvider(coroutinesRule.testDispatcherProvider)
            .coreComponent(DaggerCoreComponent
                .factory()
                .create())
            .build()
            .getInjectTest()
            .news()
    }

    @Test
    fun `addition test sample`() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `test if status is success`() {
        coroutinesRule.runBlockingTest {
            `when`(newsRemoteRepository.getCurrentNews(anyInt())).thenReturn(Result.success(News(
                1,
                listOf(
                    NewsItem("Title", "16-04-2020".parseToDate("dd-MM-yyyy"), "Subtitle", "John Doe", null, "www.123.xyz", "ABC News")
                ))
            ))

            val result = getNews.execute(1, coroutinesRule.testDispatcherProvider).getOrAwaitValue()
            assertEquals(Result.Status.SUCCESS, result.status)
        }
    }
}
