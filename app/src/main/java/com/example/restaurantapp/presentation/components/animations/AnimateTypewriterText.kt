package com.example.restaurantapp.presentation.components.animations

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.restaurantapp.presentation.theme.INTER_FONT
import kotlinx.coroutines.delay

@Composable
fun AnimateTypewriterText(
    modifier: Modifier = Modifier,
    baseText: String,
    highlightText: String,
    parts: List<String>,
) {
    val highlightStart = baseText.indexOf(highlightText)
    var partIndex by remember { mutableStateOf(0) }
    var partText by remember { mutableStateOf("") }
    val textToDisplay = "$baseText$partText"
    var selectedPartReacts by remember { mutableStateOf(listOf<Rect>()) }

    LaunchedEffect(key1 = parts) {
        while (partIndex <= parts.size) {
            val part = parts[partIndex]
            part.forEachIndexed { charIndex, _ ->
                partText = part.substring(startIndex = 0, endIndex = charIndex + 1)
                partText += " |"
                delay(100)
            }
            delay(1000)
            part.forEachIndexed { charIndex, _ ->
                partText = part.substring(startIndex = 0, endIndex = part.length - (charIndex + 1))
                partText += " |"
                delay(30)
            }
            delay(500)
            partIndex = (partIndex + 1) % parts.size
        }
    }
    Text(
        text = textToDisplay,
        style = MaterialTheme.typography.h4,
        color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
        fontSize = 14.sp,
        fontFamily = INTER_FONT,
        fontWeight = FontWeight.Black,
        modifier = Modifier
            .then(modifier),
        onTextLayout = { layoutResult ->
            val start = baseText.length
            val end = textToDisplay.count()
            selectedPartReacts = if (start < end) {
                layoutResult.getBoundingBoxesForRange(start = start, end = end - 1)
            } else {
                emptyList()
            }

            if (highlightStart >= 0) {
                selectedPartReacts = selectedPartReacts + layoutResult
                    .getBoundingBoxesForRange(
                        start = highlightStart,
                        end = highlightStart + highlightText.length
                    )
            }
        }
    )
}

fun TextLayoutResult.getBoundingBoxesForRange(
    start: Int, end: Int
): List<Rect> {
    var prevRect: Rect? = null
    var firstLineCharRect: Rect? = null
    val boundingBoxes = mutableListOf<Rect>()
    for (i in start..end) {
        val rect = getBoundingBox(i)
        val isLastRect = i == end

        // single char case
        if (isLastRect && firstLineCharRect == null) {
            firstLineCharRect = rect
            prevRect = rect
        }

        // `rect.right` is zero for the last space in each line
        // looks like an issue to me, reported: https://issuetracker.google.com/issues/197146630
        if (!isLastRect && rect.right == 0f) continue

        if (firstLineCharRect == null) {
            firstLineCharRect = rect
        } else if (prevRect != null) {
            if (prevRect.bottom != rect.bottom || isLastRect) {
                boundingBoxes.add(
                    firstLineCharRect.copy(right = prevRect.right)
                )
                firstLineCharRect = rect
            }
        }
        prevRect = rect
    }
    return boundingBoxes
}