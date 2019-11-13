package com.oxeanbits.forecastchart.core.ui.component

import android.view.View
import trikita.anvil.Anvil
import trikita.anvil.BaseDSL.v

/*
* A collection of functions using high order functions feature from kotlin
* to DRY UI code and give a elegant syntax to our custom components.
 */

inline fun <reified T : View> highOrderComponent(crossinline func: T.() -> Unit) {
    v(T::class.java) {
        val block : T = Anvil.currentView()
        block.func()
    }
}