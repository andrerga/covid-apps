package com.andre.apps.covid19updates.data.feature.summary.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryItemResponse(
    @field:Json(name = "Country")
    var countryName: String,
    @field:Json(name = "CountryCode")
    var countryCode: String,
    @field:Json(name = "Slug")
    var countrySlug: String,
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
    var totalDeaths: Int,
    @field:Json(name = "Date")
    var lastUpdated: String
)