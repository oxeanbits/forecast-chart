package com.oxeanbits.forecastchart.core.ui.component

import android.content.Context
import android.graphics.Typeface.BOLD
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import android.widget.RelativeLayout
import com.github.mikephil.charting.charts.CombinedChart
import com.oxeanbits.forecastchart.core.model.Line
import com.oxeanbits.forecastchart.core.util.Colors
import com.oxeanbits.forecastchart.core.util.ForecastChart
import trikita.anvil.Anvil
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.below
import trikita.anvil.BaseDSL.init
import trikita.anvil.BaseDSL.margin
import trikita.anvil.BaseDSL.size
import trikita.anvil.BaseDSL.text
import trikita.anvil.BaseDSL.textSize
import trikita.anvil.BaseDSL.toRightOf
import trikita.anvil.BaseDSL.typeface
import trikita.anvil.BaseDSL.v
import trikita.anvil.DSL.id
import trikita.anvil.DSL.linearLayout
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.relativeLayout
import trikita.anvil.DSL.textColor
import trikita.anvil.DSL.textView

inline fun combinedChartComponent(crossinline func: CombinedChartComponent.() -> Unit) {
    highOrderComponent(func)
}

class CombinedChartComponent(context: Context) : RelativeLayout(context), Anvil.Renderable {
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
        relativeLayout{
            size(MATCH, WRAP)
            val firstDetailLayoutId = 1
            linearLayout{
                id(firstDetailLayoutId)
                margin(30,20,0,0)
                size(WRAP, WRAP)
                orientation(VERTICAL)
                this.actualData?.let { loadChartDetails(it) }
                this.actualData?.label?.let { loadDetailsLegend(it) }
            }

            linearLayout{
                toRightOf(firstDetailLayoutId)
                margin(50,20,0,0)
                size(WRAP, WRAP)
                orientation(VERTICAL)
                this.expectedData?.let { loadChartDetails(it) }
                this.expectedData?.label?.let { loadDetailsLegend(it) }
            }

            v(CombinedChart::class.java) {
                size(MATCH, MATCH)
                below(firstDetailLayoutId)
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

    private fun loadChartDetails(data: Line){
        linearLayout{
            size(WRAP, WRAP)
            orientation(HORIZONTAL)
            textView {
                size(WRAP, WRAP)
                val lastData = data.values[data.values.lastIndex].y
                text(lastData.toString())
                textColor(Colors.TEXT_DEFAULT_BLACK)
                textSize(28f)
                typeface(null, BOLD)
            }
            textView {
                size(WRAP, WRAP)
                text(" ${data.unity}")
                textColor(Colors.TEXT_DEFAULT_GRAY)
                textSize(15f)
            }
        }
    }

    private fun loadDetailsLegend(label: String){
        textView{
            size(WRAP, WRAP)
            text(label)
            textColor(Colors.TEXT_DEFAULT_GRAY)
            textSize(15f)
        }
    }

    fun loadForecastChart(expectedData: Line, actualData: Line){
        this.expectedData = expectedData
        this.actualData = actualData
    }
}