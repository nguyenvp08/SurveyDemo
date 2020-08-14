package com.andyho.sampleapplication.manager

import android.app.Application
import android.content.Context

object SharePreferenceManager {
    const val CURRENT_ACCESS_TOKEN = "current_access_token"
    const val SHARED_PREF_NAME = "shared_data"

    lateinit var context: Application

    fun saveAccessToken(accessToken: String) {
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).edit().apply {
            putString(CURRENT_ACCESS_TOKEN, accessToken)
            apply()
        }
    }

    fun getAccessToken() = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE).getString(CURRENT_ACCESS_TOKEN, "")
}