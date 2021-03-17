package com.andre.apps.covid19updates.core.feature

import com.andre.apps.covid19updates.core.util.DispatcherProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

abstract class Usecase(
    private val dispatcherProvider: DispatcherProvider
) {

    protected fun <T> retrieveNetworkAndSync(
        dbQuery: suspend () -> T?,
        networkCall: suspend () -> Result<T>,
        saveCallResult: suspend (T) -> Unit
    ) =
        flow {
            emit(Result.loading())

            val call = withContext(dispatcherProvider.io()) { networkCall.invoke() }
            if (call.status == Result.Status.SUCCESS) {
                emit(call)

                withContext(dispatcherProvider.io()) {
                    saveCallResult(call.data!!)
                }
            } else {
                emit(Result.error(call.message!!, null))

                val dbRes = withContext(dispatcherProvider.io()) { dbQuery.invoke() }
                if (dbRes != null) {
                    emit(Result.success(dbRes))
                }
            }
        }.flowOn(dispatcherProvider.default())

    protected fun <T> retrieveNetwork(
        networkCall: suspend () -> Result<T>
    ) = flow {
        emit(Result.loading())

        val call =
            withContext(dispatcherProvider.io()) { networkCall.invoke() }
        if (call.status == Result.Status.SUCCESS) {
            emit(call)
        } else {
            emit(Result.error(call.message!!, null))
        }
    }.flowOn(dispatcherProvider.default())

    protected fun <T> retrieveLocal(
        dbQuery: suspend () -> T?
    ) = flow {
        emit(Result.loading())

        val dbRes =
            withContext(dispatcherProvider.io()) { dbQuery.invoke() }

        if (dbRes == null) {
            emit(Result.error("No data retrieved", null))
        } else {
            emit(Result.success(dbRes))
        }
    }.flowOn(dispatcherProvider.default())
}
