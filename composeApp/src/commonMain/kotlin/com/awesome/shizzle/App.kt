package com.awesome.shizzle

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.awesome.shizzle.theme.BookingTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    BookingTheme { BookingScreen(Modifier.fillMaxSize()) }
}