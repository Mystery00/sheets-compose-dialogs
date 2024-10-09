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
package com.maxkeppeler.sheets.date_time.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.date_time.models.UnitOptionEntry
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

/**
 * The value item component that can be selected.
 * @param modifier The modifier that is applied to this component.
 * @param option The option that the current component reflect.
 * @param onValueChange The listener that returns the new selection.
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun SelectionValueItem(
    modifier: Modifier = Modifier,
    option: UnitOptionEntry?,
    onValueChange: (UnitOptionEntry) -> Unit
) {
    Text(
        maxLines = 1,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .clickable { option?.let { onValueChange.invoke(it) } }
            .padding(vertical = 8.dp)
            .padding(horizontal = 8.dp),
        text = option?.labelRes?.let { stringResource(it) } ?: option?.label ?: "",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyLarge
    )
}