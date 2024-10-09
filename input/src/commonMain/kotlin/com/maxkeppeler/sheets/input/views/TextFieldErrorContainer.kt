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
package com.maxkeppeler.sheets.input.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.utils.TestTags
import com.maxkeppeker.sheets.core.utils.testTags

/**
 * A container to apply an error message to the TextField if the data is invalid.
 * @param modifier The modifier that is applied to this component.
 * @param isError If the TextField has a validation error.
 * @param errorMessage The error message of the validation.
 * @param textField The TextField below which the error is displayed.
 */
@Composable
internal fun TextFieldErrorContainer(
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String = "",
    textField: @Composable () -> Unit
) {

    Column(modifier = modifier.animateContentSize()) {
        textField()
        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .testTags(TestTags.INPUT_ITEM_TEXT_FIELD_ERROR_TEXT)
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }
}