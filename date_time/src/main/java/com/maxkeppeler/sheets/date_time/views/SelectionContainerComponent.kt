@file:OptIn(ExperimentalSnapperApi::class, ExperimentalSnapperApi::class)

package com.maxkeppeler.sheets.date_time.views

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.maxkeppeler.sheets.date_time.models.UnitOptionEntry
import com.maxkeppeler.sheets.date_time.models.UnitSelection
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior


@Composable
internal fun SelectionContainerComponent(
    height: MutableState<Int>,
    width: MutableState<Int>,
    unit: UnitSelection,
    options: List<UnitOptionEntry>,
    onValueChange: (UnitOptionEntry) -> Unit,
    heightOffsetTopPadding: Dp
) {
    val itemHeight = remember { mutableStateOf(0) }
    val itemHeightDp = LocalDensity.current.run { itemHeight.value.toDp() }

    val animatedWidth = animateIntAsState(width.value)
    val getCurrentIndex = {
        val index = options.indexOfFirst { it.value == unit.value?.value }
        val scrollIndex = if (index >= 0) index else 0
        scrollIndex
    }

    val listState = rememberLazyListState(getCurrentIndex())
    val contentPadding = PaddingValues(0.dp, itemHeightDp)

    val behavior = rememberSnapperFlingBehavior(
        lazyListState = listState,
        snapOffsetForItem = SnapOffsets.Center,
    )
    LaunchedEffect(height.value, unit, options) {
        listState.scrollToItem(getCurrentIndex())
        // Issue: https://github.com/chrisbanes/snapper/issues/32
        listState.scroll { scrollBy(20f) }
    }

    LazyColumn(
        state = listState,
        flingBehavior = behavior,
        contentPadding = contentPadding,
        modifier = Modifier
            .height(LocalDensity.current.run { height.value.toDp() })
            .padding(top = heightOffsetTopPadding)
            .graphicsLayer { alpha = 0.99F }
            .drawWithContent {
                val colorStops = arrayOf(
                    0.0f to Color.Transparent,
                    0.3f to Color.Black,
                    0.7f to Color.Black,
                    1.0f to Color.Transparent
                )
                drawContent()
                drawRect(
                    brush = Brush.verticalGradient(*colorStops),
                    blendMode = BlendMode.DstIn
                )
            }
    ) {
        options.forEach { option ->
            item {
                SelectionValueItem(
                    modifier = Modifier
                        .widthIn(min = LocalDensity.current.run { animatedWidth.value.toDp() })
                        .onGloballyPositioned { coordinates ->
                            if (itemHeight.value < coordinates.size.height) {
                                itemHeight.value = coordinates.size.height
                            }
                        },
                    option = option,
                    onValueChange = onValueChange
                )
            }
        }
    }
}