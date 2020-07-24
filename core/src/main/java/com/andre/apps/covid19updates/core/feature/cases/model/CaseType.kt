package com.andre.apps.covid19updates.core.feature.cases.model

enum class CaseType(val string: String) {
    Confirmed("confirmed"),
    Recovered("recovered"),
    Deaths("deaths")
}