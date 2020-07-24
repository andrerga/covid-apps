package com.andre.apps.covid19updates.data.feature.summary.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GlobalResponse(
    @field:Json(name = "NewConfirmed")
    var newConfirmed: Int,
    @field:Json(name = "TotalConfirmed")
    var totalConfirmed: Int,
    @field:Json(name = "NewRecovered")
    var newRecovered: Int,
    @field:Json(name = "TotalRecovered")
    var totalRecovered: Int,
    @field:Json(name = "NewDeaths")
    var newDeaths: Int,
    @field:Json(name = "TotalDeaths")
    var totalDeaths: Int
)