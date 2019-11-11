package com.oxeanbits.forecastchart.util

import java.text.SimpleDateFormat
import java.util.Date

object DateFormatter{
    fun timestampToDate(timestamp: Long): String{
        val mDate = Date()
        mDate.time = (timestamp.times(1000))
        return SimpleDateFormat("yyyy-MM-dd").format(mDate)
    }
}