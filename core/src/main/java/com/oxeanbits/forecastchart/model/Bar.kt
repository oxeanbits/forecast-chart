package com.oxeanbits.forecastchart.model

import com.github.mikephil.charting.data.BarEntry

data class Bar (
    val values: List<BarEntry>,
    val label: String,
    val color: Int
)