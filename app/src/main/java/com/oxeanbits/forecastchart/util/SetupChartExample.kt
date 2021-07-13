package com.oxeanbits.forecastchart.util

import android.graphics.Color.CYAN
import android.graphics.Color.GRAY
import android.graphics.Color.GREEN
import com.oxeanbits.forecastchart.model.ChartEntry
import com.oxeanbits.forecastchart.model.Line
import java.text.DecimalFormat

object SetupChartExample {

    private val timestamp1 = DateFormatter.stringToTimestamp("2019-11-27")
    private const val PROGRESS_1 = 25f
    private val timestamp2 = DateFormatter.stringToTimestamp("2019-11-28")
    private const val PROGRESS_2 = 25f + PROGRESS_1
    private val timestamp3 = DateFormatter.stringToTimestamp("2019-11-29")
    private const val PROGRESS_3 = 25f + PROGRESS_2
    private val timestamp4 = DateFormatter.stringToTimestamp("2019-11-30")
    private const val PROGRESS_4 = 0f + PROGRESS_3
    private val timestamp5 = DateFormatter.stringToTimestamp("2019-12-01")
    private const val PROGRESS_5 = 25f + PROGRESS_4


    fun getExpectedObj(): Line{
        val expectedEntry = listOf(
            ChartEntry(timestamp1, PROGRESS_1),
            ChartEntry(timestamp2, PROGRESS_2), ChartEntry(timestamp3, PROGRESS_3),
            ChartEntry(timestamp4, PROGRESS_4), ChartEntry(timestamp5, PROGRESS_5))

        return Line(expectedEntry, "Production Target", CYAN, false)
    }

    fun getActualObj(): Line {
        val workedEntry = listOf(ChartEntry(timestamp1, 25f), ChartEntry(timestamp2, 35f),
            ChartEntry(timestamp3, 50f)
        )

        return Line(workedEntry, "Actual", GREEN, false)
    }

    fun getForecastedObj(): Line {
        val forecastedEntry = listOf(ChartEntry(timestamp3,50f), ChartEntry(timestamp4, 75f),
            ChartEntry(timestamp5, 100f))

        return Line(forecastedEntry, "Forecasted", GRAY, true)
    }

    fun getEndDateObj(): ChartEntry {
        return ChartEntry(timestamp5, 100f)
    }

    fun getDecimalFormat(): DecimalFormat {
        val decimalFormat = DecimalFormat()
        decimalFormat.minimumFractionDigits = 2
        decimalFormat.maximumFractionDigits = 2

        return decimalFormat
    }
}