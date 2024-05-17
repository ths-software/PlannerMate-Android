package com.PlannerMate.utilities

import android.text.format.DateFormat
import java.util.Calendar

fun convertMillisToLocalDate(millis: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = millis
    val dateFormat = DateFormat.getDateFormat(/* context */ null)
    return dateFormat.format(calendar.time)
}