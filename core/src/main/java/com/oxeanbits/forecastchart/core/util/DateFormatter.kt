package com.oxeanbits.forecastchart.core.util

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter


object DateFormatter{

    fun stringToTimestamp(date: String): Float{
        return LocalDate.parse(date).atStartOfDay()
            .toInstant(ZoneOffset.UTC).epochSecond.toFloat()
    }

    fun timestampToDate(timestamp: Long): String{
        val mDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.UTC)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return mDate.format(formatter)
    }
}