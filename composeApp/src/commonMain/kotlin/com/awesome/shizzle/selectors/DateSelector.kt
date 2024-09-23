package com.awesome.shizzle.selectors

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.datetime.Instant
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelector(
    hasNeverSelected: Boolean,
    modifier: Modifier = Modifier,
    onDateSelected: (Instant) -> Unit,
) {
    var dateDialogVisible by remember { mutableStateOf(false) }
    val dateState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()
    SuggestionChip(
        onClick = {
            dateDialogVisible = true
        },
        label = {
            AnimatedContent(hasNeverSelected) { target ->
                Text(text = (if (target) "Select a" else "Change") + " date")
            }
        }
    )
    if (dateDialogVisible) {
        DatePickerDialog(
            modifier = modifier,
            onDismissRequest = { dateDialogVisible = false },
            confirmButton = {
                Button(
                    onClick = {
                        dateDialogVisible = false
                        dateState.selectedDateMillis
                            ?.let {
                                Instant.fromEpochMilliseconds(
                                    it +
                                            timePickerState.hour.hours.inWholeMilliseconds +
                                            timePickerState.minute.minutes.inWholeMilliseconds
                                )
                            }
                            ?.let(onDateSelected)
                    }
                ) {
                    Text(text = "Confirm")
                }
            }
        ) {
            DatePicker(
                state = dateState,
                showModeToggle = false,
                headline = {
                    TimeInput(state = timePickerState)
                },
                title = null,
            )
        }
    }
}