package com.andre.apps.covid19updates.core.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.parseToString(format: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(this)
}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun String.parseToDate(format: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"): Date {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.parse(this)
}

fun Date.atStartOfTheDay(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.time
}

fun Date.addDays(days: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.DATE, days)
    return calendar.time
}

fun Date.addHours(hours: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.add(Calendar.HOUR_OF_DAY, hours)
    return calendar.time
}