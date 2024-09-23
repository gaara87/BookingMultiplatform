package com.awesome.shizzle.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import booking.composeapp.generated.resources.Res
import booking.composeapp.generated.resources.ic_calendar
import booking.composeapp.generated.resources.ic_human
import booking.composeapp.generated.resources.ic_location_pin
import com.awesome.shizzle.theme.BookingColors
import com.awesome.shizzle.theme.BookingTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BookingDetails(
    state: BookingDetailsState,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(containerColor = BookingColors.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(6.dp),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
            verticalArrangement = spacedBy(24.dp)
        ) {
            BookingDetailRow(
                icon = Res.drawable.ic_human,
                title = state.massageName,
                subtitle = state.duration,
            )
            BookingDetailRow(
                icon = Res.drawable.ic_calendar,
                title = state.dateTime,
                subtitle = state.day,
            )
            BookingDetailRow(
                icon = Res.drawable.ic_location_pin,
                title = state.locationName,
                subtitle = state.locationAddress,
            )
        }
    }
}

data class BookingDetailsState(
    val massageName: String,
    val duration: String,
    val dateTime: String,
    val day: String,
    val locationName: String,
    val locationAddress: String,
)

@Composable
private fun BookingDetailRow(
    icon: DrawableResource,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Image(
            modifier = Modifier
                .background(
                    color = BookingColors.Vanilla,
                    shape = RoundedCornerShape(16.dp),
                )
                .padding(12.dp),
            painter = painterResource(icon),
            contentDescription = null,
        )
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = subtitle,
                color = BookingColors.subtleText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Suppress("unused")
@Preview
@Composable
private fun PreviewBookingDetailsComposable() {
    BookingTheme {
        BookingDetails(
            state = BookingDetailsState(
                massageName = "Massage Name",
                duration = "Duration",
                dateTime = "Date Time",
                day = "Day",
                locationName = "Location Name",
                locationAddress = "Location Address",
            )
        )
    }
}