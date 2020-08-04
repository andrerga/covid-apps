package com.andre.apps.covid19updates

import com.andre.apps.covid19updates.core.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class CoroutinesTestRule : TestWatcher() {

    val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    val scope: TestCoroutineScope = TestCoroutineScope(dispatcher)

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        scope.cleanupTestCoroutines()
    }

    fun pause() {
        scope.pauseDispatcher()
    }

    fun resume() {
        scope.resumeDispatcher()
    }

    val testDispatcherProvider = object : DispatcherProvider {

        override fun main(): CoroutineDispatcher = dispatcher

        override fun default(): CoroutineDispatcher = dispatcher

        override fun io(): CoroutineDispatcher = dispatcher

        override fun unconfined(): CoroutineDispatcher = dispatcher
    }
}
