package com.oxeanbits.forecastchart.util

import android.graphics.Color
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.utils.ColorTemplate
import com.oxeanbits.forecastchart.core.model.Line
import com.oxeanbits.forecastchart.core.util.DateFormatter

object SetupChartExample{

    val timestamp1 = DateFormatter.stringToTimestamp("2019-11-27")
    val progress1 = 25f
    val timestamp2 = DateFormatter.stringToTimestamp("2019-11-28")
    val progress2 = 25f + progress1
    val timestamp3 = DateFormatter.stringToTimestamp("2019-11-29")
    val progress3 = 25f + progress2
    val timestamp4 = DateFormatter.stringToTimestamp("2019-11-30")
    val progress4 = 0f + progress3
    val timestamp5 = DateFormatter.stringToTimestamp("2019-12-01")
    val progress5 = 25f + progress4
    val timestamp6 = DateFormatter.stringToTimestamp("2019-12-02")
    val timestamp7 = DateFormatter.stringToTimestamp("2019-12-03")
    val timestamp8 = DateFormatter.stringToTimestamp("2019-12-04")
    val timestamp9 = DateFormatter.stringToTimestamp("2019-12-05")
    val timestamp10 = DateFormatter.stringToTimestamp("2019-12-06")


    fun getExpectedObj(): Line{
        val expectedEntry = arrayListOf(Entry(timestamp1, progress1),
            Entry(timestamp2, progress2), Entry(timestamp3,progress3),
            Entry(timestamp4, progress4), Entry(timestamp5, progress5))

        return Line(expectedEntry, "Production Target", ColorTemplate.getHoloBlue(), false)
    }

    fun getActualObj(): Line{
        val workedEntry= arrayListOf(Entry(timestamp1, 25f), Entry(timestamp2, 35f),
            Entry(timestamp3, 50f)
        )

        return Line(workedEntry, "Actual", Color.GREEN, false)
    }

    fun getForecastedObj(): Line{
        val forecastedEntry = arrayListOf(Entry(timestamp3,50f), Entry(timestamp4, 75f),
            Entry(timestamp5, 100f))

        return Line(forecastedEntry, "Forecasted", Color.GRAY, true)
    }

    fun getEndDateObj(): BarEntry{
        return BarEntry(timestamp5, 100f)
    }
}