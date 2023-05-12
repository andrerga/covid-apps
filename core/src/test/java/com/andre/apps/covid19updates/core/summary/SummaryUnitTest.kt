package com.andre.apps.covid19updates.core.summary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.andre.apps.covid19updates.core.CoroutinesTestRule
import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.core.feature.summary.model.CountryItem
import com.andre.apps.covid19updates.core.feature.summary.model.Home
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryLocalRepository
import com.andre.apps.covid19updates.core.feature.summary.repo.SummaryRemoteRepository
import com.andre.apps.covid19updates.core.feature.summary.usecase.GetSummary
import com.andre.apps.covid19updates.core.getOrAwaitValue
import com.andre.apps.covid19updates.core.runBlockingTest
import com.andre.apps.covid19updates.core.util.parseToDate
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SummaryUnitTest {

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    @Mock
    lateinit var remoteRepository: SummaryRemoteRepository

    @Mock
    lateinit var localRepository: SummaryLocalRepository

    private lateinit var getSummary: GetSummary

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        getSummary = DaggerSummaryTestComponent
            .factory()
            .create(coroutinesRule.testDispatcherProvider, remoteRepository, localRepository)
            .getSummary()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(5, 2 + 3)
    }

    @Test
    fun test_ExecuteMock() {
        coroutinesRule.runBlockingTest {
            `when`(remoteRepository.getAllSummary()).thenReturn(Result.success(
                Home(
                    newConfirmed = 100282,
                    totalConfirmed = 1162857,
                    newDeaths = 5658,
                    totalDeaths = 63263,
                    newRecovered = 15405,
                    totalRecovered = 230845,
                    lastUpdated = "2020-04-05T06:37:00Z".parseToDate(),
                    countries = listOf(
                        CountryItem(
                            countryName = "Albania",
                            countrySlug = "AL",
                            newConfirmed = 29,
                            totalConfirmed = 333,
                            newRecovered = 10,
                            totalRecovered = 99,
                            newDeaths = 3,
                            totalDeaths = 20,
                            lastUpdated = "2020-04-05T06:37:00Z".parseToDate()
                        )
                    )
                )
            ))

            val result = getSummary.execute().getOrAwaitValue()
            assertEquals(Result.Status.SUCCESS, result.status)
        }
    }
}
