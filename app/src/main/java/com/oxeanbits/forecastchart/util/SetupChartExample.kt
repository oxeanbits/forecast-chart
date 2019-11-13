package com.oxeanbits.forecastchart.util

import android.content.Context
import android.graphics.Color
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.utils.ColorTemplate
import com.oxeanbits.forecastchart.core.model.Bar
import com.oxeanbits.forecastchart.core.model.Line
import com.oxeanbits.forecastchart.core.util.SetupChart.configChart
import com.oxeanbits.forecastchart.core.util.SetupChart.setupEndBarDataSet
import com.oxeanbits.forecastchart.core.util.SetupChart.setupLineDataSet
import java.text.SimpleDateFormat

object SetupChartExample{
    const val THOUS = 1000f

    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    val timestamp1 = (dateFormat.parse("2019-01-01").time)/THOUS
    val progress1 = 25f
    val timestamp2 = (dateFormat.parse("2019-01-02").time)/THOUS
    val progress2 = 25f + progress1
    val timestamp3 = (dateFormat.parse("2019-01-03").time)/THOUS
    val progress3 = 25f + progress2
    val timestamp4 = (dateFormat.parse("2019-01-04").time)/THOUS
    val progress4 = 0f + progress3
    val timestamp5 = (dateFormat.parse("2019-01-05").time)/THOUS
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

    fun createForecastChartExample(context: Context, combinedChart: CombinedChart){
        val expectedEntry = arrayListOf(
            Entry(timestamp1, progress1), Entry(timestamp2, progress2),
            Entry(timestamp3,progress3), Entry(timestamp4, progress4),
            Entry(timestamp5, progress5))

        val workedEntry = arrayListOf(Entry(timestamp1, 25f), Entry(timestamp2,30f),
            Entry(timestamp3,45f), Entry(timestamp4, 70f))
        val endDateEntry = arrayListOf(BarEntry(timestamp5, progress5))

        val expectedObj = Line(expectedEntry, "Production Target", ColorTemplate.getHoloBlue(), "m³")
        val actualObj= Line(workedEntry, "Actual", Color.GREEN, "m³")
        val endDateObj = Bar(endDateEntry, "End Date", Color.RED, "m³")

        val production = setupLineDataSet(expectedObj)
        val actual = setupLineDataSet(actualObj)
        val endDate = setupEndBarDataSet(endDateObj)

        val lineData = LineData()
        lineData.addDataSet(actual)
        lineData.addDataSet(production)

        val barData = BarData()
        barData.addDataSet(endDate)
        barData.barWidth = 3000f

        val combinedData = CombinedData()
        combinedData.setData(lineData)
        combinedData.setData(barData)

        combinedChart.data = combinedData
        configChart(context, combinedChart, "m²")
    }
}