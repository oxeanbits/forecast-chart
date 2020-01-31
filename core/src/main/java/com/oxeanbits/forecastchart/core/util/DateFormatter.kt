package com.oxeanbits.forecastchart.core.util

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter


object DateFormatter{

    const val DEFAULT_DATE_FORMAT: String = "dd/MM/yyyy"

    fun stringToTimestamp(date: String): Float{
        return LocalDate.parse(date).atStartOfDay()
            .toInstant(ZoneOffset.UTC).epochSecond.toFloat()
    }

    fun timestampToDate(timestamp: Long, dateFormat: String): String{
        val mDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneOffset.UTC)
        val formatter = DateTimeFormatter.ofPattern(dateFormat)
        return mDate.format(formatter)
    }
}