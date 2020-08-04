package com.andre.apps.covid19updates.data.feature.summary.api

import com.andre.apps.covid19updates.data.base.ApiClient
import com.andre.apps.covid19updates.data.base.get
import com.andre.apps.covid19updates.data.feature.summary.response.HomeResponse
import javax.inject.Inject

class HomeService @Inject constructor(private val api: ApiClient) {

    suspend fun getSummary(): HomeResponse {
        return api.call("summary")
            .get()
    }
}