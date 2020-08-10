package com.andre.apps.covid19updates.data.base

import com.andre.apps.covid19updates.data.BuildConfig.*
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import okio.ByteString
import org.json.JSONObject
import timber.log.Timber
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ApiClient(private val client: OkHttpClient, private val moshi: Moshi) {

    internal fun call(url: String, type: UrlType = UrlType.Default): Builder {
        val reqBuilder = Request.Builder()
        reqBuilder
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")

        val urlBuilder = when (type) {
            UrlType.News -> HttpUrl.parse(BASE_URL_NEWS + url)!!
                .newBuilder()
                .addQueryParameter("apiKey", TOKEN_NEWS)
            else -> HttpUrl.parse(BASE_URL_API + url)!!
                .newBuilder()
        }

        return Builder(
            reqBuilder,
            client,
            moshi,
            urlBuilder
        )
    }

    internal class Builder(
        internal val builder: Request.Builder,
        private val client: OkHttpClient,
        private val moshi: Moshi,
        private val urlBuilder: HttpUrl.Builder
    ) {

        // Byte order mark. See: https://stackoverflow.com/a/2223926
        private val utf8Bom: ByteString = ByteString.decodeHex("EFBBBF")

        internal fun addQueryParam(key: String, value: String): Builder {
            urlBuilder.addQueryParameter(key, value)
            return this
        }

        internal fun addHeader(name: String, value: String): Builder {
            builder.addHeader(name, value)
            return this
        }

        internal fun requestPost(obj: JSONObject): Builder {
            builder.url(urlBuilder.build())

            val body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                obj.toString()
            )
            builder.post(body)
            return this
        }

        internal fun requestGet(): Builder {
            builder.url(urlBuilder.build())
            builder.get()
            return this
        }

        internal suspend inline fun <reified T> executeRequest(): T {
            val call = client.newCall(builder.build())
            return call.await()
        }

        private suspend inline fun <reified T> Call.await(): T {
            return suspendCancellableCoroutine { continuation ->

                enqueue(object : Callback {

                    override fun onResponse(call: Call, response: Response) {
                        val value = response.body()!!
                        try {
                            val source = value.source()

                            if (source.rangeEquals(0, utf8Bom)) {
                                source.skip(utf8Bom.size().toLong())
                            }

                            val reader = JsonReader.of(source)
                            val result = moshi.adapter(T::class.java)
                                .fromJson(reader)!!

                            continuation.resume(result)
                        } catch (e: IOException) {
                            val sourceString = value.string()
                            if (sourceString.isNullOrEmpty()) {
                                continuation.resumeWithException(
                                    CancellationException("Unknown Error")
                                )
                            } else {
                                val returns = sourceString.split("[ ,]+")
                                if (returns.count() == 1) {
                                    continuation.resumeWithException(
                                        CancellationException(returns[0])
                                    )
                                    return
                                }

                                if (returns.count() < 3) {
                                    continuation.resumeWithException(
                                        CancellationException("Unknown Error")
                                    )
                                    return
                                }

                                val chunk = returns[1]
                                val message = chunk.substring(
                                    chunk.indexOf("=") + 1
                                )
                                continuation.resumeWithException(
                                    CancellationException(message)
                                )
                            }
                        } finally {
                            response.close()
                        }
                    }

                    override fun onFailure(call: Call, e: IOException) {
                        continuation.resumeWithException(
                            CancellationException(e.message)
                        )
                    }
                })

                continuation.invokeOnCancellation {
                    try {
                        cancel()
                    } catch (ignore: Throwable) {
                        Timber.e(ignore)
                    }
                }
            }
        }
    }
}

internal suspend inline fun <reified T> ApiClient.Builder.get() =
    this.requestGet().executeRequest<T>()

internal suspend inline fun <reified T> ApiClient.Builder.get(
    block: ApiClient.Builder.() -> Unit
) =
    this.apply(block).requestGet().executeRequest<T>()

internal suspend inline fun <reified T> ApiClient.Builder.post(
    obj: JSONObject
) =
    this.requestPost(obj).executeRequest<T>()

internal suspend inline fun <reified T> ApiClient.Builder.post(
    obj: JSONObject,
    block: ApiClient.Builder.() -> Unit
) =
    this.apply(block).requestPost(obj).executeRequest<T>()
