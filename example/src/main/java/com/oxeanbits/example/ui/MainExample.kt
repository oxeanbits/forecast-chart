package com.oxeanbits.forecastchart.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.oxeanbits.example.util.SetupChartExample
import trikita.anvil.Anvil
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.init
import trikita.anvil.BaseDSL.size
import trikita.anvil.BaseDSL.v
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.relativeLayout
import trikita.anvil.RenderableView

class MainExample : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getView())
    }

    private fun getView(): View {
        return object : RenderableView(this) {
            override fun view() {
                relativeLayout {
                    size(MATCH, MATCH)
                    orientation(LinearLayout.VERTICAL)

                    v(com.github.mikephil.charting.charts.CombinedChart(
                        applicationContext).javaClass,
                        Anvil.Renderable{
                            size(MATCH, 500)
                            init{
                                SetupChartExample.createForecastChartExample(
                                    applicationContext,
                                    Anvil.currentView())
                            }
                    })
                }
            }
        }
    }
}