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
                size(MATCH, 500)
                margin(10)
                init {
                    this.combinedChart = Anvil.currentView()
                    this.combinedChart?.let {
                        expectedData?.let { it1 ->
                            actualData?.let { it2 ->
                                ForecastChart.createForecastChart(context,
                                    it, it1, it2
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun loadForecastChart(expectedData: Line, actualData: Line){
        this.expectedData = expectedData
        this.actualData = actualData
    }
}