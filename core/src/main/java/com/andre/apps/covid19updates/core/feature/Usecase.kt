package com.andre.apps.covid19updates.core.feature

import com.andre.apps.covid19updates.core.util.DispatcherProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

abstract class Usecase {

    protected fun <T> retrieveNetworkAndSync(
        dbQuery: () -> T?,
        networkCall: suspend () -> Result<T>,
        saveCallResult: suspend (T) -> Unit,
        dispatcherProvider: DispatcherProvider
    ) =
        flow {
            emit(Result.loading())

            val call = withContext(dispatcherProvider.default()) { networkCall.invoke() }
            if (call.status == Result.Status.SUCCESS) {
                emit(call)

                withContext(dispatcherProvider.default()) {
                    saveCallResult(call.data!!)
                }
            } else {
                emit(Result.error(call.message!!, null))

                val dbRes = withContext(dispatcherProvider.main()) { dbQuery.invoke() }
                if (dbRes != null) {
                    emit(Result.success(dbRes))
                }
            }
        }

    protected fun <T> retrieveNetwork(
        networkCall: suspend () -> Result<T>,
        dispatcherProvider: DispatcherProvider
    ) = flow {
        emit(Result.loading())

        val call =
            withContext(dispatcherProvider.default()) { networkCall.invoke() }

        if (call.status == Result.Status.SUCCESS) {
            emit(call)
        } else {
            emit(Result.error(call.message!!, null))
        }
    }

    protected fun <T> retrieveLocal(
        dbQuery: () -> T?
    ) = flow {
        emit(Result.loading())

        val dbRes = dbQuery.invoke()
        if (dbRes == null) {
            emit(Result.error(message = "No data retrieved"))
        } else {
            emit(Result.success(dbRes))
        }
    }
}