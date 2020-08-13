package com.andre.apps.covid19updates.data.base

import io.objectbox.BoxStore
import io.objectbox.TxCallback
import java.util.concurrent.Callable
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LocalDb @Inject constructor(private val boxStore: BoxStore) {

    internal suspend inline fun write(crossinline block: ((boxStore: BoxStore) -> Unit)) {

        return suspendCoroutine { continuation ->
            val call = Runnable {
                block.invoke(boxStore)
            }
            val callback = TxCallback<Void> { _, error ->
                if (error != null) {
                    continuation.resumeWithException(error)
                } else {
                    continuation.resume(Unit)
                }
            }

            boxStore.runInTxAsync(call, callback)
        }
    }

    internal suspend inline fun <reified T> read(crossinline block: ((boxStore: BoxStore) -> T?)): T? {

        return suspendCoroutine { continuation ->
            val call = Callable {
                block.invoke(boxStore)
            }
            val callback = TxCallback<T?> { result, error ->
                if (error != null) {
                    continuation.resumeWithException(error)
                } else {
                    continuation.resume(result)
                }
            }

            boxStore.callInTxAsync(call, callback)
        }
    }
}
