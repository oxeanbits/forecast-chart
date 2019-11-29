package com.oxeanbits.forecastchart.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.threetenabp.AndroidThreeTen
import com.oxeanbits.forecastchart.core.ui.component.forecastChartComponent
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
        return object : RenderableView(this) {
            override fun view() {
                linearLayout{
                    size(MATCH, MATCH)
                    orientation(LinearLayout.VERTICAL)

                    forecastChartComponent{
                        size(MATCH, 350)
                        loadForecastChart(
                            SetupChartExample.getExpectedObj(),
                            SetupChartExample.getActualObj(),
                            SetupChartExample.getForecastedObj(),
                            SetupChartExample.getEndDateObj(),
                            "m³"
                        )
                    }

                    forecastChartComponent {
                        size(MATCH, 350)
                        loadForecastChart(
                            SetupChartExample.getActualObj(),
                            SetupChartExample.getForecastedObj(),
                            SetupChartExample.getEndDateObj(),
                            "m³"
                        )
                    }
                }
            }
        }
    }
}