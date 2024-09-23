@file:Suppress("MemberVisibilityCanBePrivate")

package com.awesome.shizzle.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

object BookingColors {
    @Stable
    val Grey = Color(0xA3000000)

    @Stable
    val Vanilla = Color(0xFFF2F0E9)

    val White = Color.White

    val subtleText: Color
        get() = Grey

    val bg: Color
        get() = White
}