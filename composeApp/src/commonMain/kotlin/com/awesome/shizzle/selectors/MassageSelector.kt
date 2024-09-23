package com.awesome.shizzle.selectors

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.awesome.shizzle.details.MassageType
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun MassageSelector(
    hasNeverSelected: Boolean,
    onMassageTypeSelected: (MassageType) -> Unit,
) {
    var dropdownExpanded by remember { mutableStateOf(false) }

    SuggestionChip(
        onClick = { dropdownExpanded = true },
        label = {
            AnimatedContent(hasNeverSelected) { target ->
                Text(
                    text = (if (target) "Select a" else "Change") + " massage type"
                )
            }
        }
    )
    DropdownMenu(
        expanded = dropdownExpanded,
        onDismissRequest = { dropdownExpanded = false },
    ) {
        MassageType.entries.forEach {
            DropdownMenuItem(
                onClick = {
                    dropdownExpanded = false
                    onMassageTypeSelected(it)
                },
                text = {
                    Text(stringResource(it.description))
                },
            )
        }
    }

}
