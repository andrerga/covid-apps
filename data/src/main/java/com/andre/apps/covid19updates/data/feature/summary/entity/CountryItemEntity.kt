package com.andre.apps.covid19updates.data.feature.summary.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.NameInDb
import java.util.*

@Entity
data class CountryItemEntity(
    @Id
    var id: Long = 0,
    @NameInDb("country_name")
    var countryName: String,
    @NameInDb("country_slug")
    var countrySlug: String,
    @NameInDb("new_confirmed")
    var newConfirmed: Int,
    @NameInDb("total_confirmed")
    var totalConfirmed: Int,
    @NameInDb("new_recovered")
    var newRecovered: Int,
    @NameInDb("total_recovered")
    var totalRecovered: Int,
    @NameInDb("new_deaths")
    var newDeaths: Int,
    @NameInDb("total_deaths")
    var totalDeaths: Int,
    @NameInDb("last_updated")
    var lastUpdated: Date
)
