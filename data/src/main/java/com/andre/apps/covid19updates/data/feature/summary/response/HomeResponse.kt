package com.andre.apps.covid19updates.data.feature.summary.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeResponse(
    @field:Json(name = "Global")
    var global: GlobalResponse,
    @field:Json(name = "Countries")
    var countries: List<CountryItemResponse>,
    @field:Json(name = "Date")
    var lastUpdated: String
)