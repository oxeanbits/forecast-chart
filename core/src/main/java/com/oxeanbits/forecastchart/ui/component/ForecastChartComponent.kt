package com.oxeanbits.forecastchart.ui.component

import android.content.Context
import android.graphics.Color.CYAN
import android.graphics.Color.GRAY
import android.graphics.Color.GREEN
import android.graphics.Color.RED
import android.graphics.Typeface.BOLD
import androidx.core.content.ContextCompat.getColor
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.data.BarEntry
import com.oxeanbits.forecastchart.core.R
import com.oxeanbits.forecastchart.model.Bar
import com.oxeanbits.forecastchart.model.ChartEntry
import com.oxeanbits.forecastchart.model.Line
import com.oxeanbits.forecastchart.ui.anvil.LinearLayoutComponent
import com.oxeanbits.forecastchart.util.DateFormatter.DEFAULT_DATE_FORMAT
import com.oxeanbits.forecastchart.util.ForecastChart
import trikita.anvil.Anvil.currentView
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.WRAP
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
import java.text.DecimalFormat

inline fun forecastChartComponent(crossinline func: ForecastChartComponent.() -> Unit) {
    highOrderComponent(func)
}

class ForecastChartComponent(context: Context) : LinearLayoutComponent(context) {
    private var combinedChart: CombinedChart? = null
    private var expectedData: Line = emptyLine()
    private var actualData: Line? = null
    private var forecastedData: Line? = null
    private var endDateData: Bar = emptyBar()
    private var unit: String = ""
    private var dateFormat: String = DEFAULT_DATE_FORMAT
    private var decimalFormat: DecimalFormat = DecimalFormat()
    private var zoomEnabled: Boolean = false
    private var detailsEnable: Boolean = false

    override fun view() {
        orientation(VERTICAL)

        if(detailsEnable) {
            renderDetailsLayout()
        }
        renderChart()
    }

    private fun renderDetailsLayout() {
        linearLayout {
            size(WRAP, WRAP)
            orientation(HORIZONTAL)

            val expectedData = this.expectedData
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
                } else if(forecastedData.values.isNotEmpty()){
                    renderChartDetails(forecastedData)
                    renderDetailsLegend(forecastedData.label)
                }
            }
        }
    }

    private fun renderChartDetails(data: Line){
        linearLayout {
            size(WRAP, WRAP)
            orientation(HORIZONTAL)
            textView {
                size(WRAP, WRAP)
                val lastData = data.values[data.values.lastIndex].y
                text(lastData.toString())
                textColor(getColor(context, R.color.black))
                textSize(28f)
                typeface(null, BOLD)
            }
            textView {
                size(WRAP, WRAP)
                text(" $unit")
                textColor(getColor(context, R.color.grey))
                textSize(15f)
            }
        }
    }

    private fun renderDetailsLegend(label: String){
        textView {
            size(WRAP, WRAP)
            text(label)
            textColor(getColor(context, R.color.grey))
            textSize(15f)
        }
    }

    private fun renderChart() {
        val expectedData = this.expectedData
        val actualData = this.actualData ?: return
        val forecastedData = this.forecastedData ?: return

        v(CombinedChart::class.java) {
            size(MATCH, MATCH)
            this.combinedChart = currentView()
            val combinedChart = this.combinedChart ?: return@v
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
                    dateFormat,
                    decimalFormat,
                    zoomEnabled
                )
            }
        }
    }

    private fun emptyLine(): Line {
        return Line(listOf(), "", 0, false)
    }

    private fun emptyBar(): Bar {
        return Bar(listOf(), "", 0)
    }

    fun expectedLine(arrayData: List<ChartEntry>, label: String,
                     color: Int = CYAN, forecasted: Boolean = false) {
        this.expectedData = Line(arrayData, label, color, forecasted)
    }

    fun actualLine(arrayData: List<ChartEntry>, label: String,
                   color: Int = GREEN, forecasted: Boolean = false) {
        this.actualData = Line(arrayData, label, color, forecasted)
        render()
        combinedChart?.invalidate()
    }

    fun forecastedLine(arrayData: List<ChartEntry>, label: String,
                       color: Int = GRAY, forecasted: Boolean = true) {
        this.forecastedData = Line(arrayData, label, color, forecasted)
    }

    fun endDateBar(x: Float, y: Float, label: String, color: Int = RED) {
        this.endDateData = Bar(listOf(BarEntry(x, y)), label, color)
    }

    fun unit(unit: String) {
        this.unit = unit
    }

    fun dateFormat(dateFormat: String) {
        this.dateFormat = dateFormat
    }

    fun decimalFormat(decimalFormat: DecimalFormat) {
        this.decimalFormat = decimalFormat
    }

    fun zoomEnabled(zoomEnabled: Boolean) {
        this.zoomEnabled = zoomEnabled
    }

    fun detailsEnable(detailsEnable: Boolean) {
        this.detailsEnable = detailsEnable
    }
}
