package com.oxeanbits.forecastchart.core.ui.component

import android.content.Context
import android.widget.LinearLayout
import com.github.mikephil.charting.charts.CombinedChart
import com.oxeanbits.forecastchart.core.model.Line
import com.oxeanbits.forecastchart.core.util.ForecastChart
import trikita.anvil.Anvil
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.init
import trikita.anvil.BaseDSL.margin
import trikita.anvil.BaseDSL.size
import trikita.anvil.BaseDSL.v
import trikita.anvil.DSL.linearLayout
import trikita.anvil.DSL.orientation

inline fun combinedChartComponent(crossinline func: CombinedChartComponent.() -> Unit) {
    highOrderComponent(func)
}

class CombinedChartComponent(context: Context) : LinearLayout(context), Anvil.Renderable {
    private var combinedChart: CombinedChart? = null
    private var expectedData: Line? = null
    private var actualData: Line? = null

    public override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Anvil.mount(this, this)
    }

    public override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Anvil.unmount(this, false)
    }

    override fun view() {
        linearLayout{
            size(MATCH, MATCH)
            orientation(VERTICAL)

            v(CombinedChart::class.java) {
                size(MATCH, MATCH)
                margin(10)
                init {
                    this.combinedChart = Anvil.currentView()

                    val combinedChart =  this.combinedChart ?: return@init
                    val expectedData = this.expectedData ?: return@init
                    val actualData = this.actualData ?: return@init
                    ForecastChart.createForecastChart(context, combinedChart, expectedData, actualData)
                }
            }
        }
    }

    fun loadForecastChart(expectedData: Line, actualData: Line){
        this.expectedData = expectedData
        this.actualData = actualData
    }
}