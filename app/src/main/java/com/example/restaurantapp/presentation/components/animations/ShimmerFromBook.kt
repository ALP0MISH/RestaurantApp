package com.example.restaurantapp.presentation.components.animations

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class ShimmerAnimationType {
    FADE, TRANSLATE,  VERTICAL
}

@Preview
@Composable
fun ShimmerList(
    modifier: Modifier = Modifier
) {
    var shimmerAnimationType by remember { mutableStateOf(ShimmerAnimationType.FADE) }

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim by transition.animateFloat(
        initialValue = 100f,
        targetValue = 600f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = LinearEasing),
            RepeatMode.Restart
        ), label = ""
    )

    val colorAnim by transition.animateColor(
        initialValue = Color.LightGray.copy(alpha = 0.6f),
        targetValue = Color.LightGray,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Restart
        ), label = ""
    )

    val list = if (shimmerAnimationType != ShimmerAnimationType.TRANSLATE) {
        listOf(colorAnim, colorAnim.copy(alpha = 0.5f))
    } else {
        listOf(Color.LightGray.copy(alpha = 0.6f), Color.LightGray)
    }

    val dpValue = if (shimmerAnimationType != ShimmerAnimationType.FADE) {
        translateAnim.dp
    } else {
        2000.dp
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        SpacerHeight(size = 12.dp)
        ShimmerTopItem(list, dpValue.value, shimmerAnimationType == ShimmerAnimationType.VERTICAL)
        ShimmerItemSearch(
            list,
            dpValue.value,
            shimmerAnimationType == ShimmerAnimationType.VERTICAL
        )
        ShimmerItemBig(list, dpValue.value, shimmerAnimationType == ShimmerAnimationType.VERTICAL)
        ShimmerItemVertical(
            list,
            dpValue.value,
            shimmerAnimationType == ShimmerAnimationType.VERTICAL
        )
        ShimmerItem(list, dpValue.value, shimmerAnimationType == ShimmerAnimationType.VERTICAL)
    }
}

@Composable
fun ShimmerItem(lists: List<Color>, floatAnim: Float = 0f, isVertical: Boolean) {
    val brush = if (isVertical) Brush.verticalGradient(lists, 0f, floatAnim) else
        Brush.horizontalGradient(lists, 0f, floatAnim)
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(brush = brush)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(8.dp)
                    .background(brush = brush)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(8.dp)
                    .background(brush = brush)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(8.dp)
                    .background(brush = brush)
            )
        }
    }
}

@Composable
fun ShimmerItemBig(lists: List<Color>, floatAnim: Float = 0f, isVertical: Boolean) {
    val brush = if (isVertical) Brush.verticalGradient(lists, 0f, floatAnim) else
        Brush.horizontalGradient(lists, 0f, floatAnim)
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(brush = brush)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun ShimmerTopItem(lists: List<Color>, floatAnim: Float = 0f, isVertical: Boolean) {
    val brush = if (isVertical) Brush.verticalGradient(lists, 0f, floatAnim) else
        Brush.horizontalGradient(lists, 0f, floatAnim)
    Row(
        modifier = Modifier.padding(16.dp).padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .background(brush = brush)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(8.dp)
                    .background(brush = brush)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(8.dp)
                    .background(brush = brush)
            )
        }
    }
}

@Composable
fun ShimmerItemSearch(lists: List<Color>, floatAnim: Float = 0f, isVertical: Boolean) {
    val brush = if (isVertical) Brush.verticalGradient(lists, 0f, floatAnim) else
        Brush.horizontalGradient(lists, 0f, floatAnim)
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(45.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(brush = brush)
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun ShimmerItemVertical(lists: List<Color>, floatAnim: Float = 0f, isVertical: Boolean) {
    val brush = if (isVertical) Brush.verticalGradient(lists, 0f, floatAnim) else
        Brush.horizontalGradient(lists, 0f, floatAnim)
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .height(250.dp)
                .width(160.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(brush = brush)
        )
        Spacer(
            modifier = Modifier
                .height(250.dp)
                .width(160.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(brush = brush)
        )
        Spacer(
            modifier = Modifier
                .height(250.dp)
                .width(160.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(brush = brush)
        )
    }
}
