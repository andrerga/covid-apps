package com.andre.apps.covid19updates.data.feature.cases.api

import com.andre.apps.covid19updates.data.base.ApiClient
import com.andre.apps.covid19updates.data.base.get
import com.andre.apps.covid19updates.data.feature.cases.response.CaseByCountryResponse
import javax.inject.Inject

class ByCountryService @Inject constructor(private val api: ApiClient) {

    suspend fun getCasesByCountry(slug: String, case: String): List<CaseByCountryResponse> {

        return api.call("dayone/country/$slug/status/$case")
            .get()
    }
}