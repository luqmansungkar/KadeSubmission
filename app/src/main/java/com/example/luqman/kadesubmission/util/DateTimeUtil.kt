package com.example.luqman.kadesubmission.util

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil{
    fun formatDateTime(dateTime: String?, fromFormat: String, toFormat: String): String{
        return try{
            val fromSimpleDateFormat = SimpleDateFormat(fromFormat, Locale.getDefault())

            val oldDateTime = fromSimpleDateFormat.parse(dateTime)
            val toSimpleDateFormat = SimpleDateFormat(toFormat, Locale.getDefault())

            toSimpleDateFormat.format(oldDateTime)
        }catch (e: Exception){
            dateTime ?: ""
        }

    }
}