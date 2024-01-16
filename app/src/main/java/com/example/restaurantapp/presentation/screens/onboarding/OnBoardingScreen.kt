package com.example.restaurantapp.presentation.screens.onboarding

import com.example.restaurantapp.presentation.components.animations.AnimateTypewriterText
import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurantapp.R
import com.example.restaurantapp.presentation.components.animations.SpacerHeight
import com.example.restaurantapp.presentation.components.animations.advancedShadow
import com.example.restaurantapp.presentation.screens.onboarding.models.OnBoardingPagerItem
import com.example.restaurantapp.presentation.theme.INTER_FONT
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.launch

@SuppressLint("ResourceType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navigateToLoginScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val onboardings = OnBoardingPagerItem.onboardingItems()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { onboardings.size }
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier,
            state = pagerState
        ) { position ->
            when (val onboarding = onboardings[position]) {
                is OnBoardingPagerItem.Welcome -> WelcomeOnBoarding(
                    titleId = onboarding.textId,
                    imageId = onboarding.imageId
                )

                is OnBoardingPagerItem.OnBoarding -> {
                    OnboardingPage(page = onboarding)
                }
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(4f))
        val page = onboardings[pagerState.currentPage]
        if (page is OnBoardingPagerItem.OnBoarding) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                text = stringResource(id = page.textId),
                style = TextStyle.Default.copy(
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            SpacerHeight(16.dp)
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                text = stringResource(id = page.description),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        OnBoardingAnimatedTextField(
            page = onboardings[pagerState.currentPage],
            onNextPage = { isLastPage ->
                if (isLastPage) navigateToLoginScreen()
                else scope.launch { pagerState.animateScrollToPage(pagerState.currentPage.inc()) }
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        HorizontalPagerIndicator(
            modifier = Modifier.padding(bottom = 24.dp),
            pagerState = pagerState,
            pageCount = onboardings.size,
            indicatorWidth = 30.dp,
            indicatorHeight = 4.dp,
            inactiveColor = Color.Gray,
            activeColor = Color.White,
            spacing = 8.dp
        )
    }
}

@Composable
fun OnboardingPage(
    page: OnBoardingPagerItem.OnBoarding,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = page.imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun WelcomeOnBoarding(
    @StringRes titleId: Int,
    @DrawableRes imageId: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(25.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = titleId),
                fontFamily = INTER_FONT,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun OnBoardingAnimatedTextField(
    page: OnBoardingPagerItem,
    onNextPage: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(horizontal = 36.dp)
            .fillMaxWidth()
            .advancedShadow(
                color = Color.White,
                alpha = 0.9f,
                shadowBlurRadius = 40.dp
            )
            .height(52.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable { onNextPage(page.isLastPage) }
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (page.isLastPage) Spacer(modifier = Modifier.weight(1f))
            AnimateTypewriterText(
                modifier = Modifier,
                baseText = String(),
                highlightText = "|",
                parts = listOf(stringResource(id = page.buttonTextId))
            )
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(visible = !page.isLastPage) {
                IconButton(onClick = { onNextPage(page.isLastPage) }) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun WelcomeOnBoardingPreview() {
    MaterialTheme {
        WelcomeOnBoarding(
            titleId = R.string.welcome,
            imageId = R.drawable.welcome_image
        )
    }
}