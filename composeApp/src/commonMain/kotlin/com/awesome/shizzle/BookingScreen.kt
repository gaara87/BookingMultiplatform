package com.awesome.shizzle

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.awesome.shizzle.details.BookingDetails
import com.awesome.shizzle.details.BookingDetailsDataState
import com.awesome.shizzle.details.reduce
import com.awesome.shizzle.selectors.DateSelector
import com.awesome.shizzle.selectors.DurationSelector
import com.awesome.shizzle.selectors.MassageSelector
import com.awesome.shizzle.theme.BookingColors

@Composable
fun BookingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = spacedBy(16.dp),
    ) {
        var bookingDetailsDataState by remember { mutableStateOf(BookingDetailsDataState()) }
        BookingDetails(
            state = reduce(bookingDetailsDataState),
            modifier = Modifier.fillMaxWidth(),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = spacedBy(8.dp)
        ) {
            Text(
                text = "Enter your booking details",
                style = MaterialTheme.typography.titleLarge,
            )
            MassageSelector(
                hasNeverSelected = bookingDetailsDataState.massageType == null,
                onMassageTypeSelected = {
                    bookingDetailsDataState = bookingDetailsDataState.copy(
                        massageType = it,
                    )
                }
            )
            DurationSelector(
                selectedDuration = bookingDetailsDataState.duration,
                onDurationSelected = {
                    bookingDetailsDataState = bookingDetailsDataState.copy(
                        duration = it,
                    )
                }
            )
            DateSelector(
                hasNeverSelected = bookingDetailsDataState.date == null,
                onDateSelected = { date ->
                    bookingDetailsDataState = bookingDetailsDataState.copy(
                        date = date,
                    )
                },
            )
            var locationName by remember { mutableStateOf("") }
            TextField(
                value = locationName,
                onValueChange = {
                    locationName = it
                    bookingDetailsDataState = bookingDetailsDataState.copy(
                        _locationName = it,
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyMedium,
                placeholder = {
                    Text(
                        text = "Location Name",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BookingColors.subtleText,
                    )
                }
            )
            var locationAddress by remember { mutableStateOf("") }
            TextField(
                value = locationAddress,
                onValueChange = {
                    locationAddress = it
                    bookingDetailsDataState = bookingDetailsDataState.copy(
                        _locationAddress = it,
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.bodyMedium,
                placeholder = {
                    Text(
                        text = "Location Address",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BookingColors.subtleText,
                    )
                }
            )
        }
    }
}
