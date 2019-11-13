package com.oxeanbits.forecastchart.core.util

import android.content.Context
import android.graphics.Color
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.LineData
import com.oxeanbits.forecastchart.core.model.Bar
import com.oxeanbits.forecastchart.core.model.Line
import com.oxeanbits.forecastchart.core.util.SetupChart.configChart
import com.oxeanbits.forecastchart.core.util.SetupChart.setupEndBarDataSet
import com.oxeanbits.forecastchart.core.util.SetupChart.setupLineDataSet

object ForecastChart{

    fun createForecastChart(context: Context, combinedChart: CombinedChart, expectedData: Line, actualData: Line) {
        val actual = setupLineDataSet(actualData)
        val production = setupLineDataSet(expectedData)

        val endX = expectedData.values[expectedData.values.lastIndex].x
        val endY = expectedData.values[expectedData.values.lastIndex].y

        val endDateEntry = arrayListOf(BarEntry(endX, endY))
        val endDateObj = Bar(endDateEntry, "End Date", Color.RED, expectedData.unity)
        val endDate = setupEndBarDataSet(endDateObj)

        val lineData = LineData()
        lineData.addDataSet(actual)
        lineData.addDataSet(production)

        val barData = BarData()
        barData.addDataSet(endDate)
        barData.barWidth = 3000f

        val combinedData = CombinedData()
        combinedData.setData(lineData)

        combinedChart.data = combinedData
        configChart(context, combinedChart, expectedData.unity)
    }
}