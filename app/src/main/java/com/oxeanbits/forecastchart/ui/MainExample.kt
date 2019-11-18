package com.oxeanbits.forecastchart.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.oxeanbits.forecastchart.core.ui.component.combinedChartComponent
import com.oxeanbits.forecastchart.util.SetupChartExample
import trikita.anvil.RenderableView

class MainExample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getView())
    }

    private fun getView(): View {
        return object : RenderableView(this) {
            override fun view() {
                combinedChartComponent{
                    loadForecastChart(
                       SetupChartExample.getExpectedObj(),
                       SetupChartExample.getActualObj()
                   )
                }
            }
        }
    }
}