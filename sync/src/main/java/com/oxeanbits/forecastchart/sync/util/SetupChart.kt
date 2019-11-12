package com.oxeanbits.forecastchart.sync.util

import android.content.Context
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.oxeanbits.forecastchart.sync.R
import com.oxeanbits.forecastchart.sync.ui.ForecastMarkerView

object SetupChart{
    fun setupLineDataSet(values: ArrayList<Entry>, label: String, color: Int): LineDataSet {
        val lineDataSet = LineDataSet(values, label)
        lineDataSet.color = color
        lineDataSet.lineWidth = 2f
        lineDataSet.setDrawCircles(false)
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawFilled(true)
        lineDataSet.fillColor = color
        lineDataSet.fillAlpha = 45

        return lineDataSet
    }
    
    fun setupEndBarDataSet(values: ArrayList<BarEntry>, label: String, color: Int): BarDataSet {
        val barDataSet = BarDataSet(values, label)
        barDataSet.color = color
        barDataSet.setDrawValues(false)

        return barDataSet
    }

    fun configChart(context: Context, combinedChart: CombinedChart, unity: String){
        combinedChart.legend.form = Legend.LegendForm.LINE
        combinedChart.legend.textSize = 15f

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