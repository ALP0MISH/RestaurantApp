package com.example.socialapp.data.repositories

import android.content.Context
import com.example.restaurantapp.domain.models.UserDomain
import com.example.restaurantapp.domain.repository.CurrentUserRepository
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val IS_ONBOARDING_PASSED = "is_onboarding_passed"
private const val CURRENT_USER_NAME = "CURRENT_USER_NAME"
private const val CURRENT_USER_OBJECT_ID = "CURRENT_USER_OBJECT_ID"
private const val USER_PREF = "USER_PREF"

class CurrentUserRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CurrentUserRepository {

    private val sharedPreferences by lazy(LazyThreadSafetyMode.NONE) {
        context.getSharedPreferences(IS_ONBOARDING_PASSED, Context.MODE_PRIVATE)
    }

    private val userSharedPreferencesby by lazy(LazyThreadSafetyMode.NONE) {
        context.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE)
    }

    override fun saveCurrentUser(user: UserDomain) {
        userSharedPreferencesby.edit()
            .putString(CURRENT_USER_NAME, Gson().toJson(user))
            .apply()
    }

    override fun fetchCurrentUser(): UserDomain {
        return try {
            val json = userSharedPreferencesby.getString(CURRENT_USER_NAME, String()) ?: String()
            Gson().fromJson(json, UserDomain::class.java)
        } catch (e: Exception) {
            UserDomain.unknown
        }
    }

    override fun clearCurrentUser() {
        userSharedPreferencesby
            .edit()
            .clear()
            .apply()
    }

    override fun isOnboardingPassed(): Boolean {
        return sharedPreferences.getBoolean(IS_ONBOARDING_PASSED, false)
    }

    override fun setOnboardingShowed() {
        sharedPreferences.edit().putBoolean(IS_ONBOARDING_PASSED, true).apply()
    }

    override fun clearOnBoarding() {
        sharedPreferences.edit().putBoolean(IS_ONBOARDING_PASSED, false).apply()
    }

    override fun saveUserObjectId(userObject: String) {
        val prefEditor = sharedPreferences.edit()
        prefEditor.putString(CURRENT_USER_OBJECT_ID, userObject)
        prefEditor.apply()
    }

    override fun getSavedUserId(): String {
        return sharedPreferences.getString(CURRENT_USER_OBJECT_ID, String()) ?: String()
    }
}