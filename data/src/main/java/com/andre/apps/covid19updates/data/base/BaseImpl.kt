package com.andre.apps.covid19updates.data.base

import com.andre.apps.covid19updates.core.feature.Result
import timber.log.Timber

abstract class BaseImpl {

    protected suspend fun <T1, T2> getResult(call: suspend () -> T1, mapper: (T1) -> T2): Result<T2> {
        return try {
            val response = call.invoke()
            Result.success(mapper(response))
        } catch (e: Exception) {
            error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        Timber.e(message)
        return Result.error("Call failed: $message")
    }
}