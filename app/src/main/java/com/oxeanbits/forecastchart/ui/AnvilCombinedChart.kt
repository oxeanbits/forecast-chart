package com.oxeanbits.forecastchart.ui

import android.content.Context
import com.github.mikephil.charting.charts.CombinedChart
import trikita.anvil.Anvil
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.size
import trikita.anvil.BaseDSL.v

class AnvilCombinedChart(val context: Context) : Anvil.Renderable {

    override fun view() {
        v(CombinedChart(context).javaClass,
            Anvil.Renderable {
                size(MATCH, 500)
            })

    }
}