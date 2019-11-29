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
    const val LINE_WIDTH = 2f
    const val FILL_ALPHA = 45
    const val LEGEND_SIZE = 15f
    const val SIDE_OFFSET = 40f

    fun setupLineDataSet(line: Line): LineDataSet {
        val lineDataSet = LineDataSet(line.values, line.label)
        lineDataSet.color = line.color
        lineDataSet.lineWidth = LINE_WIDTH
        lineDataSet.setDrawCircles(false)
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawFilled(true)

        if(line.forecasted ){
            lineDataSet.enableDashedLine(10f, 10f, 0f)
        }else{
            lineDataSet.fillColor = line.color
            lineDataSet.fillAlpha = FILL_ALPHA
        }

        return lineDataSet
    }

    fun setupEndBarDataSet(bar: Bar): BarDataSet {
        val barDataSet = BarDataSet(bar.values, bar.label)
        barDataSet.color = bar.color
        barDataSet.setDrawValues(false)

        return barDataSet
    }

    fun configChart(context: Context, combinedChart: CombinedChart, unit: String){
        combinedChart.legend.form = Legend.LegendForm.LINE
        combinedChart.legend.xEntrySpace = LEGEND_SIZE
        combinedChart.legend.textSize = LEGEND_SIZE

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
            unit
        )
        combinedChart.marker = marker
        combinedChart.extraLeftOffset = SIDE_OFFSET
        combinedChart.extraRightOffset = SIDE_OFFSET
    }
}