package com.andre.apps.covid19updates.core.cases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.andre.apps.covid19updates.core.*
import com.andre.apps.covid19updates.core.feature.Result
import com.andre.apps.covid19updates.core.feature.cases.model.CaseByCountry
import com.andre.apps.covid19updates.core.feature.cases.model.CaseType
import com.andre.apps.covid19updates.core.feature.cases.repo.ByCountryRemoteRepository
import com.andre.apps.covid19updates.core.feature.cases.usecase.GetCasesByCountry
import com.andre.apps.covid19updates.core.util.parseToDate
import kotlinx.coroutines.flow.collect
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ByCountryUnitTest {

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    @Mock
    lateinit var repository: ByCountryRemoteRepository

    lateinit var getCasesByCountry: GetCasesByCountry

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        getCasesByCountry = DaggerByCountryTestComponent
            .factory()
            .create(repository)
            .getCasesByCountry()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_ExecuteMock() {
        coroutinesRule.runBlockingTest {
            `when`(repository.getDataByCases("switzerland", CaseType.Confirmed)).thenReturn(Result.success(listOf(
                CaseByCountry(cases = 20505, date = "2020-04-04T00:00:00Z".parseToDate()),
                CaseByCountry(cases = 19606, date = "2020-04-03T00:00:00Z".parseToDate())
            )))

            val result = getCasesByCountry.execute("switzerland", CaseType.Confirmed, coroutinesRule.testDispatcherProvider).getOrAwaitValue()
            assertEquals(Result.Status.SUCCESS, result.status)
        }
    }
}