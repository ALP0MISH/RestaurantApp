package com.example.restaurantapp.presentation.screens.onboarding.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.restaurantapp.R

sealed class OnBoardingPagerItem(
    @StringRes open val buttonTextId: Int, open val isLastPage: Boolean = false
) {
    data class Welcome(
        @DrawableRes val imageId: Int,
        @StringRes val textId: Int,
        @StringRes override val buttonTextId: Int,
    ) : OnBoardingPagerItem(buttonTextId)

    data class OnBoarding(
        @DrawableRes val imageId: Int,
        @StringRes val textId: Int,
        @StringRes override val buttonTextId: Int,
        @StringRes val description: Int,
        override val isLastPage: Boolean = false
    ) : OnBoardingPagerItem(buttonTextId, isLastPage)

    companion object {
        fun onboardingItems() = listOf(
            Welcome(
                imageId = R.drawable.welcome_image,
                textId = R.string.welcome,
                buttonTextId = R.string.letsgo
            ),
            OnBoarding(
                imageId = R.drawable.table_image,
                textId = R.string.second_onboarding_text,
                description = R.string.second_onboarding_description,
                buttonTextId = R.string.got_it
            ),
            OnBoarding(
                imageId = R.drawable.takeaway,
                textId = R.string.first_onboarding_text,
                description = R.string.first_onboarding_description,
                buttonTextId = R.string.get_started,
                isLastPage = true
            )
        )
    }
}