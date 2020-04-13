package com.oxeanbits.forecastchart.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import com.oxeanbits.forecastchart.ui.component.forecastChartComponent
import com.oxeanbits.forecastchart.util.SetupChartExample
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.size
import trikita.anvil.DSL.linearLayout
import trikita.anvil.DSL.orientation
import trikita.anvil.RenderableView

class MainExample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        setContentView(getView())
    }

    private fun getView(): View {
        val expectedLine = SetupChartExample.getExpectedObj()
        val actualLine = SetupChartExample.getActualObj()
        val forecastedLine = SetupChartExample.getForecastedObj()
        val endDateBar = SetupChartExample.getEndDateObj()

        return object : RenderableView(this) {
            override fun view() {
                linearLayout{
                    size(MATCH, MATCH)
                    orientation(LinearLayout.VERTICAL)

                    forecastChartComponent{
                        size(MATCH, 350)
                        expectedLine(expectedLine.values, expectedLine.label,
                            expectedLine.color, expectedLine.forecasted)
                        actualLine(actualLine.values, actualLine.label,
                            actualLine.color, actualLine.forecasted)
                        forecastedLine(forecastedLine.values, forecastedLine.label,
                            forecastedLine.color, forecastedLine.forecasted)
                        endDateBar(endDateBar.x, endDateBar.y)
                        unit("m³")
                        zoomEnabled(true)
                    }

                    forecastChartComponent {
                        size(MATCH, 350)
                        actualLine(actualLine.values, actualLine.label,
                            actualLine.color, actualLine.forecasted)
                        forecastedLine(forecastedLine.values, forecastedLine.label,
                            forecastedLine.color, forecastedLine.forecasted)
                        endDateBar(endDateBar.x, endDateBar.y)
                        unit("m³")
                        dateFormat("MM/dd/yyyy")
                        decimalFormat(SetupChartExample.getDecimalFormat())
                        detailsEnable(true)
                    }
                }
            }
        }
    }
}