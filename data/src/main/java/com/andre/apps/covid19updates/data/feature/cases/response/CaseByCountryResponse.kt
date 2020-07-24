package com.andre.apps.covid19updates.data.feature.cases.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CaseByCountryResponse(
    @field:Json(name = "Country")
    val countryName: String,
    @field:Json(name = "CountryCode")
    val countryCode: String,
    @field:Json(name = "Lat")
    val lat: String,
    @field:Json(name = "Lon")
    val lng: String,
    @field:Json(name = "Cases")
    val cases: Int,
    @field:Json(name = "Date")
    val date: String
)