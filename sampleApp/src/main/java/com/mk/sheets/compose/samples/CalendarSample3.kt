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
@file:OptIn(ExperimentalMaterial3Api::class)

package com.mk.sheets.compose.samples

import android.util.Range
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.util.toRange
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.calendar.utils.LocalDateRange
import kotlinx.datetime.toKotlinLocalDate
import java.time.LocalDate

@Composable
internal fun CalendarSample3(closeSelection: UseCaseState.() -> Unit) {

    val timeBoundary = LocalDate.now().let { now ->
        val start = now.minusYears(2).toKotlinLocalDate()
        val end = now.toKotlinLocalDate()
        start..end
    }
    val selectedRange = remember {
        val default = LocalDate.now().minusYears(2).let { time ->
            val start = time.plusDays(5).toKotlinLocalDate()
            val end = time.plusDays(8).toKotlinLocalDate()
            start..end
        }
        mutableStateOf(LocalDateRange(default.start, default.endInclusive))
    }

    CalendarDialog(
        state = rememberUseCaseState(visible = true, true, onCloseRequest = closeSelection),
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            boundary = timeBoundary,
            style = CalendarStyle.MONTH,
        ),
        selection = CalendarSelection.Period(
            selectedRange = selectedRange.value
        ) { startDate, endDate ->
            selectedRange.value = LocalDateRange(startDate, endDate)
        },
    )
}