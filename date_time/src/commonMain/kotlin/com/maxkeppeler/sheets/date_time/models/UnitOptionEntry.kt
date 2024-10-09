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
package com.maxkeppeler.sheets.date_time.models

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import com.maxkeppeker.sheets.core.utils.JvmSerializable

/**
 * A class that acts as a value item that can be selected for a unit.
 * @param value The actual value.
 * @param label The textual representation of the value.
 * @param labelRes The textual representation of the value by resource.
 */
@OptIn(ExperimentalResourceApi::class)
data class UnitOptionEntry constructor(
    val value: Int,
    val label: String? = null,
    val labelRes: StringResource? = null
) : JvmSerializable
