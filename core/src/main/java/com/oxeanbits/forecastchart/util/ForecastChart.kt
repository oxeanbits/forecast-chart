package com.oxeanbits.forecastchart.util

import android.content.Context
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.LineData
import com.oxeanbits.forecastchart.model.Bar
import com.oxeanbits.forecastchart.model.Line
import com.oxeanbits.forecastchart.util.SetupChart.configChart
import com.oxeanbits.forecastchart.util.SetupChart.setupEndBarDataSet
import com.oxeanbits.forecastchart.util.SetupChart.setupLineDataSet
import java.text.DecimalFormat

object ForecastChart {
    private const val BAR_WIDTH = 1000f

    fun createForecastChart(context: Context, combinedChart: CombinedChart, expectedData: Line,
                            actualData: Line, forecastedData: Line, endDateData: Bar,
                            unit: String, dateFormat: String, decimalFormat: DecimalFormat,
                            zoomEnabled: Boolean) {

        val production = setupLineDataSet(expectedData)
        val actual = setupLineDataSet(actualData)
        val forecasted = setupLineDataSet(forecastedData)

        val endDate = setupEndBarDataSet(endDateData)

        val lineData = LineData()
        lineData.addDataSet(production)
        lineData.addDataSet(actual)
        lineData.addDataSet(forecasted)

        val barData = BarData()
        barData.addDataSet(endDate)
        barData.barWidth = BAR_WIDTH * (actualData.values.size + forecastedData.values.size)

        val combinedData = CombinedData()
        combinedData.setData(lineData)
        combinedData.setData(barData)

        combinedChart.data = combinedData
        configChart(context, combinedChart, unit, dateFormat, decimalFormat, zoomEnabled)
    }
}