package com.oxeanbits.forecastchart.core.ui.component

import android.content.Context
import android.graphics.Typeface.BOLD
import android.widget.LinearLayout
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.oxeanbits.forecastchart.core.model.Line
import com.oxeanbits.forecastchart.core.util.Colors
import com.oxeanbits.forecastchart.core.util.ForecastChart
import trikita.anvil.Anvil
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.init
import trikita.anvil.BaseDSL.margin
import trikita.anvil.BaseDSL.size
import trikita.anvil.BaseDSL.textSize
import trikita.anvil.BaseDSL.typeface
import trikita.anvil.BaseDSL.v
import trikita.anvil.DSL.linearLayout
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.text
import trikita.anvil.DSL.textColor
import trikita.anvil.DSL.textView

inline fun forecastChartComponent(crossinline func: ForecastChartComponent.() -> Unit) {
    highOrderComponent(func)
}

class ForecastChartComponent(context: Context) : LinearLayout(context), Anvil.Renderable {
    private var combinedChart: CombinedChart? = null
    private var expectedData: Line = emptyLine()
    private var actualData: Line? = null
    private var forecastedData: Line? = null
    private var endDateData: BarEntry = emptyBar()
    private var unit: String = ""
    private var zoomEnabled: Boolean = false

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

            renderDetailsLayout()
            renderChart()
        }
    }

    private fun renderDetailsLayout(){
        linearLayout {
            size(WRAP, WRAP)
            orientation(HORIZONTAL)

            val expectedData = this.expectedData ?: return@linearLayout
            val actualData = this.actualData ?: return@linearLayout
            val forecastedData = this.forecastedData ?: return@linearLayout

            linearLayout {
                margin(30, 20, 0, 0)
                size(WRAP, WRAP)
                orientation(VERTICAL)
                if (actualData.values.isNotEmpty()) {
                    renderChartDetails(actualData)
                    renderDetailsLegend(actualData.label)
                }
            }

            linearLayout {
                margin(50, 20, 0, 0)
                size(WRAP, WRAP)
                orientation(VERTICAL)
                if (expectedData.values.isNotEmpty()) {
                    renderChartDetails(expectedData)
                    renderDetailsLegend(expectedData.label)
                }else if(forecastedData.values.isNotEmpty()){
                    renderChartDetails(forecastedData)
                    renderDetailsLegend(forecastedData.label)
                }
            }
        }
    }

    private fun renderChartDetails(data: Line){
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
                text(" ${unit}")
                textColor(Colors.TEXT_DEFAULT_GRAY)
                textSize(15f)
            }
        }
    }

    private fun renderDetailsLegend(label: String){
        textView{
            size(WRAP, WRAP)
            text(label)
            textColor(Colors.TEXT_DEFAULT_GRAY)
            textSize(15f)
        }
    }

    private fun renderChart(){
        v(CombinedChart::class.java) {
            size(MATCH, MATCH)
            init {
                this.combinedChart = Anvil.currentView()
                val combinedChart = this.combinedChart ?: return@init
                val expectedData = this.expectedData
                val actualData = this.actualData ?: return@init
                val forecastedData = this.forecastedData ?: return@init
                val endDateData = this.endDateData

                if(actualData.values.isNotEmpty() || forecastedData.values.isNotEmpty()
                    || expectedData.values.isNotEmpty()) {
                    ForecastChart.createForecastChart(
                        context,
                        combinedChart,
                        expectedData,
                        actualData,
                        forecastedData,
                        endDateData,
                        unit,
                        zoomEnabled
                    )
                }
            }
        }
    }

    private fun emptyLine(): Line{
        return Line(arrayListOf(), "", 0, false)
    }

    private fun emptyBar(): BarEntry{
        return BarEntry(-1f, 0f)
    }

    fun expectedLine(arrayData: ArrayList<Entry>, label: String,
                     color: Int, forecasted: Boolean = false){
        this.expectedData = Line(arrayData, label, color, forecasted)
    }

    fun actualLine(arrayData: ArrayList<Entry>, label: String,
                   color: Int, forecasted: Boolean = false){
        this.actualData = Line(arrayData, label, color, forecasted)
    }

    fun forecastedLine(arrayData: ArrayList<Entry>, label: String,
                       color: Int, forecasted: Boolean = true){
        this.forecastedData = Line(arrayData, label, color, forecasted)
    }

    fun endDateBar(x: Float, y: Float){
        this.endDateData = BarEntry(x, y)
    }

    fun unit(unit: String){
        this.unit = unit
    }

    fun zoomEnabled(zoomEnabled: Boolean){
        this.zoomEnabled = zoomEnabled
    }
}