package com.awesome.shizzle.details

import androidx.compose.runtime.Composable
import booking.composeapp.generated.resources.Res
import booking.composeapp.generated.resources.workout_title_deep_tissue
import booking.composeapp.generated.resources.workout_title_full_body
import booking.composeapp.generated.resources.workout_title_head_scalp
import booking.composeapp.generated.resources.workout_title_leg_foot
import booking.composeapp.generated.resources.workout_title_prenatal
import booking.composeapp.generated.resources.workout_title_total_back
import booking.composeapp.generated.resources.workout_title_upper_body
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.offsetAt
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

data class BookingDetailsDataState(
    val date: Instant? = null,
    val duration: Duration? = null,
    val massageType: MassageType? = null,
    private val _locationName: String? = null,
    private val _locationAddress: String? = null,
) {
    @Composable
    fun massageTypeDescription() =
        massageType?.description?.let { stringResource(it) } ?: "Massage Name"

    val durationTime: String
        get() = duration?.inWholeMinutes?.toString()?.let { "$it minutes" } ?: "Duration"

    private val adjustedTime = date?.let {
        Instant.fromEpochMilliseconds(
            it.toEpochMilliseconds() -
                    TimeZone.currentSystemDefault()
                        .offsetAt(it)
                        .totalSeconds
                        .seconds
                        .inWholeMilliseconds
        ).toLocalDateTime(TimeZone.currentSystemDefault())
    }

    val dateTime: String
        get() = if (adjustedTime == null) {
            "Date Time"
        } else {
            LocalDateTime.Format {
                monthName(MonthNames.ENGLISH_FULL)
                char(' ')
                dayOfMonth()
                chars(" at ")
                amPmHour()
                char(':')
                minute()
                char(' ')
                amPmMarker("am", "pm")
            }.format(adjustedTime)
        }

    val day: String
        get() = if (adjustedTime == null) {
            "Day"
        } else {
            LocalDateTime.Format { dayOfWeek(DayOfWeekNames.ENGLISH_FULL) }.format(adjustedTime)
        }

    val locationName: String
        get() = _locationName ?: "Location Name"

    val locationAddress: String
        get() = _locationAddress ?: "Location Address"
}

enum class MassageType {
    TOTAL_BACK,
    LEG_FOOT,
    UPPER_BODY,
    FULL_BODY,
    DEEP_TISSUE,
    HEAD_SCALP,
    PRENATAL,
    ;

    val description: StringResource
        get() = when (this) {
            TOTAL_BACK -> Res.string.workout_title_total_back
            LEG_FOOT -> Res.string.workout_title_leg_foot
            UPPER_BODY -> Res.string.workout_title_upper_body
            FULL_BODY -> Res.string.workout_title_full_body
            DEEP_TISSUE -> Res.string.workout_title_deep_tissue
            HEAD_SCALP -> Res.string.workout_title_head_scalp
            PRENATAL -> Res.string.workout_title_prenatal
        }
}
