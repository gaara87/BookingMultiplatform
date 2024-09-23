package com.awesome.shizzle.details

import androidx.compose.runtime.Composable

@Composable
fun reduce(dataState: BookingDetailsDataState) = BookingDetailsState(
    massageName = dataState.massageTypeDescription(),
    duration = dataState.durationTime,
    dateTime = dataState.dateTime,
    day = dataState.day,
    locationName = dataState.locationName,
    locationAddress = dataState.locationAddress
)
