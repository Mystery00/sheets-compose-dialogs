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
package com.mk.sheets.compose.models

enum class UseCaseType(val title: String) {
    CORE("⚒️  Core Dialog"),
    INFO("ℹ️  Info Dialog"),
    RATING("✨ Rating Dialog"),
    COLOR("\uD83C\uDFA8  Color Dialog"),
    CALENDAR("\uD83D\uDCC5  Calendar Dialog"),
    CLOCK("\uD83D\uDD67  Clock Dialog"),
    DATE_TIME("\uD83D\uDCC5\uD83D\uDD67  DateTime Dialog"),
    DURATION("⌛  Duration Dialog"),
    OPTION("\uD83E\uDEA7  Option Dialog"),
    LIST("✅️  List Dialog"),
    EMOJI("\uD83D\uDE1C  Emoji Dialog"),
    INPUT("\uD83D\uDD8A️ Input Dialog"),
    STATE("\uD83D\uDD03  State Dialog")
}