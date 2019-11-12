package com.oxeanbits.forecastchart.util

import android.content.Context
import android.graphics.Color
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.LineData
import com.oxeanbits.forecastchart.model.Line
import com.oxeanbits.sync.util.SetupChart.configChart
import com.oxeanbits.sync.util.SetupChart.setupEndBarDataSet
import com.oxeanbits.sync.util.SetupChart.setupLineDataSet

object ForecastChart{

    fun createForecastChart(context: Context, combinedChart: CombinedChart, l1: Line, l2: Line) {
        val actual = setupLineDataSet(l1.values, l1.label, l1.color)
        val production = setupLineDataSet(l2.values, l2.label, l2.color)

        val endX = l2.values.get(l2.values.size-1).x
        val endY = l2.values.get(l2.values.size-1).y
        val endDateArray = arrayListOf(BarEntry(endX, endY))
        val endDate = setupEndBarDataSet(endDateArray, "End Date", Color.RED)

        val lineData = LineData()
        lineData.addDataSet(actual)
        lineData.addDataSet(production)

        val barData = BarData()
        barData.addDataSet(endDate)
        barData.barWidth = 3000f

        val combinedData = CombinedData()
        combinedData.setData(lineData)

        combinedChart.data = combinedData
        configChart(context, combinedChart, l2.unity)
    }
}