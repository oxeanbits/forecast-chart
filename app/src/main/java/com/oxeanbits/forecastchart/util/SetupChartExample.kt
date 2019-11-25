package com.oxeanbits.forecastchart.util

import android.graphics.Color
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.utils.ColorTemplate
import com.oxeanbits.forecastchart.core.model.Line
import com.oxeanbits.forecastchart.core.util.DateFormatter

object SetupChartExample{

    val timestamp1 = DateFormatter.stringToTimestamp("2019-01-01")
    val progress1 = 25f
    val timestamp2 = DateFormatter.stringToTimestamp("2019-01-02")
    val progress2 = 25f + progress1
    val timestamp3 = DateFormatter.stringToTimestamp("2019-01-03")
    val progress3 = 25f + progress2
    val timestamp4 = DateFormatter.stringToTimestamp("2019-01-04")
    val progress4 = 0f + progress3
    val timestamp5 = DateFormatter.stringToTimestamp("2019-01-05")
    val progress5 = 25f + progress4

    fun getExpectedObj(): Line{
        val expectedEntry = arrayListOf(Entry(timestamp1, progress1),
            Entry(timestamp2, progress2), Entry(timestamp3,progress3),
            Entry(timestamp4, progress4), Entry(timestamp5, progress5))

        return Line(expectedEntry, "Production Target", ColorTemplate.getHoloBlue(), "m³")

    }

    fun getActualObj(): Line{
        val workedEntry = arrayListOf(Entry(timestamp1, 25f), Entry(timestamp2,30f),
            Entry(timestamp3,45f), Entry(timestamp4, 70f))

        return Line(workedEntry, "Actual", Color.GREEN, "m³")
    }
}