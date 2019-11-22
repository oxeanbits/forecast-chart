package com.oxeanbits.forecastchart.core.util

import java.text.SimpleDateFormat
import java.util.Date

object DateFormatter{
    const val ONE_DAY = 86400
    fun timestampToDate(timestamp: Long): String{
        val mDate = Date()
        mDate.time = timestamp.times(1000) + ONE_DAY
        return SimpleDateFormat("yyyy-MM-dd").format(mDate)
    }
}