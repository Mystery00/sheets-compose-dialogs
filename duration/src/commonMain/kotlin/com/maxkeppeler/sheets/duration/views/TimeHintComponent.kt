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
package com.maxkeppeler.sheets.duration.views


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maxkeppeker.sheets.core.models.base.LibOrientation
import com.maxkeppeler.sheets.duration.utils.getFormattedHintTime
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import sheets_compose_dialogs.duration.generated.resources.Res
import sheets_compose_dialogs.duration.generated.resources.scd_duration_dialog_at_least_placeholder
import sheets_compose_dialogs.duration.generated.resources.scd_duration_dialog_at_most_placeholder

/**
 * The info component that will show a hint if the selected time is out of the specified bounds.
 * @param orientation The orientation of the view.
 * @param minTime The minimum valid time.
 * @param maxTime The maximum valid time.
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun TimeHintComponent(
    orientation: LibOrientation,
    minTime: Long? = null,
    maxTime: Long? = null,
) {

    if (minTime != null || maxTime != null) {

        val hintRes = if (minTime != null) Res.string.scd_duration_dialog_at_least_placeholder
        else Res.string.scd_duration_dialog_at_most_placeholder

        val time = minTime ?: maxTime
        ?: throw IllegalStateException("Hint is shown but min and max time values are null.")
        val values = getFormattedHintTime(time)

        TimeValueComponent(
            modifier = Modifier,
            orientation = orientation,
            values = values,
            hintValue = stringResource(hintRes)
        )
    }
}
