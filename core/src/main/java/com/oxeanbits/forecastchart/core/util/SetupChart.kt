package com.oxeanbits.forecastchart.core.util

import android.content.Context
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.LineDataSet
import com.oxeanbits.forecastchart.core.R
import com.oxeanbits.forecastchart.core.model.Bar
import com.oxeanbits.forecastchart.core.model.Line
import com.oxeanbits.forecastchart.core.ui.marker.ForecastMarkerView

object SetupChart{

    fun setupLineDataSet(line: Line): LineDataSet {
        val lineDataSet = LineDataSet(line.values, line.label)
        lineDataSet.color = line.color
        lineDataSet.lineWidth = 2f
        lineDataSet.setDrawCircles(false)
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawFilled(true)
        lineDataSet.fillColor = line.color
        lineDataSet.fillAlpha = 45

        return lineDataSet
    }

    fun setupEndBarDataSet(bar: Bar): BarDataSet {
        val barDataSet = BarDataSet(bar.values, bar.label)
        barDataSet.color = bar.color
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