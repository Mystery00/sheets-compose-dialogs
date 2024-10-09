/*
 *  Copyright (C) 2022-2024. Maximilian Keppeler (https://www.maxkeppeler.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
@file:OptIn(ExperimentalTextApi::class, ExperimentalMaterial3Api::class)

package com.maxkeppeler.sheets.clock.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.style.TextAlign
import com.maxkeppeler.sheets.clock.utils.FormatStyle
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import sheets_compose_dialogs.clock.generated.resources.Res
import sheets_compose_dialogs.clock.generated.resources.scd_clock_dialog_boundary_hint

/**
 * A component that displays a hint that the current time is out of the defined boundary, if set and invalid.
 *
 * @param valid Boolean representing the validity of the time
 * @param boundary Optional [ClosedRange] of [LocalTime] representing the time boundary
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun TimeHintComponent(
    modifier: Modifier,
    valid: Boolean,
    boundary: ClosedRange<LocalTime>? = null
) {
    if (valid || boundary == null) return

    val formatter = remember { getTimeFormatter(FormatStyle.SHORT) }
    val startTime = remember(boundary) { boundary.start.format(formatter) }
    val endTime = remember(boundary) { boundary.endInclusive.format(formatter) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = stringResource(Res.string.scd_clock_dialog_boundary_hint, startTime, endTime),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

internal expect fun getTimeFormatter(style: FormatStyle): DateTimeFormat<LocalTime>