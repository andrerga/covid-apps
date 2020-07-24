package com.andre.apps.covid19updates.core

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest

@ExperimentalCoroutinesApi
fun CoroutinesTestRule.runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
    this.scope.runBlockingTest {
        block()
    }

suspend fun <T> Flow<T>.getOrAwaitValue(): T {
    val data: T? = toList().last()

    @Suppress("UNCHECKED_CAST")
    return data as T
}