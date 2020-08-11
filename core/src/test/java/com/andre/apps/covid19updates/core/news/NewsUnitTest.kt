package com.andre.apps.covid19updates.core.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.andre.apps.covid19updates.core.CoroutinesTestRule
import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.core.feature.news.model.News
import com.andre.apps.covid19updates.core.feature.news.model.NewsItem
import com.andre.apps.covid19updates.core.feature.news.repo.NewsRemoteRepository
import com.andre.apps.covid19updates.core.feature.news.usecase.GetNews
import com.andre.apps.covid19updates.core.getOrAwaitValue
import com.andre.apps.covid19updates.core.runBlockingTest
import com.andre.apps.covid19updates.core.util.parseToDate
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsUnitTest {

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    @Mock
    lateinit var repository: NewsRemoteRepository

    private lateinit var getNews: GetNews

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        getNews = DaggerNewsTestComponent
            .factory()
            .create(repository, coroutinesRule.testDispatcherProvider)
            .getNews()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_ExecuteMock() {
        coroutinesRule.runBlockingTest {
            `when`(
                repository.getCurrentNews(
                    ArgumentMatchers.anyInt()
                )
            ).thenReturn(
                Result.success(
                    News(
                        total = 1,
                        items = listOf(
                            NewsItem(
                                headline = "Title",
                                date = "16-04-2020".parseToDate("dd-MM-yyyy"),
                                subtitle = "Subtitle",
                                editorName = "John Doe",
                                imageUrl = null,
                                contentUrl = "www.123.xyz",
                                source = "ABC News"
                            )
                        )
                    )
                ))

            val result = getNews.execute(
                1
            ).getOrAwaitValue()
            assertEquals(Result.Status.SUCCESS, result.status)
        }
    }
}
