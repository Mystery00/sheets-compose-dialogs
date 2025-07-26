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

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.Header
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.rating.RatingDialog
import com.maxkeppeler.sheets.rating.models.FeedbackTextFieldType
import com.maxkeppeler.sheets.rating.models.RatingBody
import com.maxkeppeler.sheets.rating.models.RatingConfig
import com.maxkeppeler.sheets.rating.models.RatingSelection
import com.maxkeppeler.sheets.rating.models.RatingViewStyle

@Composable
internal fun RatingSample4(closeSelection: () -> Unit) {
    RatingDialog(
        state = rememberUseCaseState(
            visible = true,
            onCloseRequest = { closeSelection() }),
        header = Header.Default(
            title = "Help Us Improve",
            icon = IconSource(Icons.Outlined.StarRate)
        ),
        body = RatingBody.Default(
            bodyText = "Love what you see? Show your support by rating us on the Play Store! Every rating counts and motivates us to keep delivering an amazing app for you."
        ),
        config = RatingConfig(
            ratingViewStyle = RatingViewStyle.CENTER,
            ratingOptionsCount = 6,
            withFeedback = true,
            feedbackOptional = false,
            feedbackTextFieldType = FeedbackTextFieldType.OUTLINED,
        ),
        selection = RatingSelection(
            onSelectRating = { rating, feedback ->
                // Handle rating selection
            },
        )
    )
}