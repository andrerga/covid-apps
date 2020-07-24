package com.andre.apps.covid19updates.core.feature.summary.model

import java.util.*

data class Home(
    var newConfirmed: Int,
    var totalConfirmed: Int,
    var newRecovered: Int,
    var totalRecovered: Int,
    var newDeaths: Int,
    var totalDeaths: Int,
    var lastUpdated: Date,
    var countries: List<CountryItem>
)