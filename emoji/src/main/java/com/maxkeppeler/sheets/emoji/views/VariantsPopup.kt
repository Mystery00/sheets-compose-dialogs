package com.maxkeppeler.sheets.emoji.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.Popup
import com.vanniktech.emoji.Emoji
import com.maxkeppeler.sheets.core.R as RC

/**
 * A popup that shows the various variants of an emoji.
 * @param show The state used to show and hide the dialog.
 * @param size The size that is used as a constraint for the popup.
 * @param baseEmoji The base emoji that was clicked.
 * @param selectedEmoji The emoji that is currently selected.
 * @param onClick The listener that returns the selected emoji.
 */
@Composable
internal fun VariantsPopup(
    show: MutableState<Boolean>,
    size: MutableState<IntSize>,
    baseEmoji: Emoji,
    selectedEmoji: Emoji?,
    onClick: (Emoji) -> Unit
) {
    if (show.value) {
        val itemSize = LocalDensity.current.run { size.value.width.toDp() }
        Popup(
            alignment = Alignment.Center,
            onDismissRequest = { show.value = false },
        ) {
            Box(Modifier.wrapContentWidth()) {
                Surface(
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(RC.dimen.scd_small_50))
                        .widthIn(max = itemSize * 6),
                    shape = MaterialTheme.shapes.medium,
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = dimensionResource(RC.dimen.scd_small_50),
                ) {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(dimensionResource(RC.dimen.scd_normal_100)),
                        columns = GridCells.Adaptive(itemSize),
                        userScrollEnabled = false,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        items(baseEmoji.variants, key = { it.unicode }) { emoji ->
                            EmojiItemComponent(
                                emoji = emoji,
                                selectedEmoji = selectedEmoji,
                                onClick = onClick
                            )
                        }
                    }
                }
            }
        }
    }
}