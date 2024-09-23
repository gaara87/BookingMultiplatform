package com.awesome.shizzle.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import booking.composeapp.generated.resources.Lato_Regular
import booking.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun BookingTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = MaterialTheme.typography.copy(
            bodyMedium = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = FontFamily(Font(Res.font.Lato_Regular))
            ),
            titleLarge = MaterialTheme.typography.titleLarge.copy(
                fontFamily = FontFamily(Font(Res.font.Lato_Regular))
            ),
        ),
        content = content,
    )
}
