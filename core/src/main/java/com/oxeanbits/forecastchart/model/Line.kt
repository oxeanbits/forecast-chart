package com.oxeanbits.forecastchart.model

import com.github.mikephil.charting.data.Entry

data class Line (
    val values: List<ChartEntry>,
    val label: String,
    val color: Int,
    val forecasted: Boolean
) {
    fun valuesToEntry() = values.map { Entry(it.x, it.y) }
}