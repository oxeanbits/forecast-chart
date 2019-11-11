package com.oxeanbits.forecastchart.util

import android.content.Context
import android.graphics.Color
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import java.text.SimpleDateFormat
import com.oxeanbits.forecastchart.R
import com.oxeanbits.forecastchart.ui.marker.ForecastMarkerView

object SetupChart{

    fun setupForecastChartTest(context: Context, combinedChart: CombinedChart){
        val timestamp1 = (SimpleDateFormat("yyyy-MM-dd").parse("2019-01-01").time)/1000F
        val progress1 = 25F
        val timestamp2 = (SimpleDateFormat("yyyy-MM-dd").parse("2019-01-02").time)/1000F
        val progress2 = 25F + progress1
        val timestamp3 = (SimpleDateFormat("yyyy-MM-dd").parse("2019-01-03").time)/1000F
        val progress3 = 25F + progress2
        val timestamp4 = (SimpleDateFormat("yyyy-MM-dd").parse("2019-01-04").time)/1000F
        val progress4 = 0F + progress3
        val timestamp5 = (SimpleDateFormat("yyyy-MM-dd").parse("2019-01-05").time)/1000F
        val progress5 = 25F + progress4

        val expected = arrayListOf(Entry(timestamp1, progress1), Entry(timestamp2, progress2),
            Entry(timestamp3,progress3), Entry(timestamp4, progress4), Entry(timestamp5, progress5))

        val worked = arrayListOf(Entry(timestamp1, 25F), Entry(timestamp2,30F),
            Entry(timestamp3,45F), Entry(timestamp4, 70F))

        val actual = setupLineDataSet(worked, "Actual", Color.GREEN)

        val production = setupLineDataSet(expected, "Production Target", ColorTemplate.getHoloBlue())

        val endDate = setupEndBarDataSet(arrayListOf(BarEntry(timestamp5, progress5)), "End Date", Color.RED)

        val lineData = LineData()
        lineData.addDataSet(actual)
        lineData.addDataSet(production)

        val barData = BarData()
        barData.addDataSet(endDate)
        barData.barWidth = 3000F

        val combinedData = CombinedData()
        combinedData.setData(lineData)
        combinedData.setData(barData)

        combinedChart.data = combinedData
        configChart(context, combinedChart, "m²")
    }

    fun setupLineDataSet(values: ArrayList<Entry>, label: String, color: Int): LineDataSet{
        val lineDataSet = LineDataSet(values, label)
        lineDataSet.color = color
        lineDataSet.lineWidth = 2F
        lineDataSet.setDrawCircles(false)
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawFilled(true)
        lineDataSet.fillColor = color
        lineDataSet.fillAlpha = 45

        return lineDataSet
    }

    fun setupEndBarDataSet(values: ArrayList<BarEntry>, label: String, color: Int): BarDataSet{
        val barDataSet = BarDataSet(values, label)
        barDataSet.color = color
        barDataSet.setDrawValues(false)

        return barDataSet
    }

    fun configChart(context: Context, combinedChart: CombinedChart, unity: String){
        combinedChart.legend.form = Legend.LegendForm.LINE

        combinedChart.xAxis.setDrawLabels(false)
        combinedChart.xAxis.setDrawGridLines(false)
        combinedChart.xAxis.setDrawAxisLine(false)

        combinedChart.axisLeft.setDrawLabels(false)
        combinedChart.axisLeft.setDrawAxisLine(false)
        combinedChart.axisLeft.setDrawGridLines(false)

        combinedChart.axisRight.setDrawLabels(false)
        combinedChart.axisRight.setDrawAxisLine(false)
        combinedChart.axisRight.setDrawGridLines(false)

        combinedChart.description = null
        combinedChart.setPinchZoom(true)

        val marker = ForecastMarkerView(
            context,
            R.layout.marker_layout,
            unity
        )
        combinedChart.marker = marker
    }
}