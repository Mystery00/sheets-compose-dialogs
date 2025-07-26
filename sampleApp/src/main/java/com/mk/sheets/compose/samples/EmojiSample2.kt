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

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.emoji.EmojiDialog
import com.maxkeppeler.sheets.emoji.models.EmojiCategoryAppearance
import com.maxkeppeler.sheets.emoji.models.EmojiConfig
import com.maxkeppeler.sheets.emoji.models.EmojiProvider
import com.maxkeppeler.sheets.emoji.models.EmojiSelection

@Composable
internal fun EmojiSample2(closeSelection: () -> Unit) {

    EmojiDialog(
        state = rememberUseCaseState(visible = true, onCloseRequest = { closeSelection() }),
        selection = EmojiSelection.Unicode(
            onPositiveClick = { emojiUnicode ->
                // Handle selection
            }
        ),
        config = EmojiConfig(
            categoryAppearance = EmojiCategoryAppearance.TEXT,
            emojiProvider = EmojiProvider.IOS
        )
    )
}