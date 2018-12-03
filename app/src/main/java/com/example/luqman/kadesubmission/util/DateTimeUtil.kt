package com.example.luqman.kadesubmission.util

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil{
    fun formatDateTime(dateTime: String?, fromFormat: String, toFormat: String): String{
        var fromSimpleDateFormat = SimpleDateFormat(fromFormat, Locale.getDefault())

        val oldDateTime = fromSimpleDateFormat.parse(dateTime)
        val toSimpleDateFormat = SimpleDateFormat(toFormat, Locale.getDefault())

        return toSimpleDateFormat.format(oldDateTime)
    }
}