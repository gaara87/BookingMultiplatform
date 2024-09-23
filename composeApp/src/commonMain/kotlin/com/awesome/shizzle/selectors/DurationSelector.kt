package com.awesome.shizzle.selectors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun DurationSelector(
    selectedDuration: Duration?,
    modifier: Modifier = Modifier,
    onDurationSelected: (Duration?) -> Unit,
) {
    FlowRow(modifier = modifier, horizontalArrangement = Arrangement.Absolute.spacedBy(4.dp)) {
        listOf(30.minutes, 45.minutes, 60.minutes, 90.minutes).forEach { duration ->
            FilterChip(
                selected = selectedDuration == duration,
                onClick = { onDurationSelected(duration) },
                label = { Text("${duration.inWholeMinutes} mins") }
            )
        }
    }
}