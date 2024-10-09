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
package com.maxkeppeler.sheets.list.views


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import sheets_compose_dialogs.list.generated.resources.Res
import sheets_compose_dialogs.list.generated.resources.scd_list_dialog_max_choices
import sheets_compose_dialogs.list.generated.resources.scd_list_dialog_min_choices

/**
 * A view that reflects the selection bounds of the list.
 * @param selection The selection configuration.
 * @param selectedOptions The list of options that are selected.
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun ListOptionBoundsComponent(
    selection: ListSelection,
    selectedOptions: List<ListOption>
) {
    val selectedAmount = selectedOptions.size
    when  {
        selection is ListSelection.Multiple
                && (selection.minChoices != null
                || selection.maxChoices != null) -> {
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                selection.minChoices?.let { minChoices ->
                    if (selectedAmount < minChoices) {
                        Text(
                            text = stringResource(Res.string.scd_list_dialog_min_choices, minChoices),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
                selection.maxChoices?.let { maxChoices ->
                    if (selectedAmount > maxChoices) {
                        Text(
                            text = stringResource(Res.string.scd_list_dialog_max_choices, maxChoices),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                MaterialTheme.typography.headlineSmall.copy(
                                    letterSpacing = 0.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                ).toSpanStyle()
                            ) {
                                append(selectedAmount.toString())
                            }
                            append("/")
                            append(maxChoices.toString())
                        },
                        style = MaterialTheme.typography.labelLarge.copy(letterSpacing = 0.5.sp)
                    )
                }
            }
        }
        else -> Unit
    }
}



