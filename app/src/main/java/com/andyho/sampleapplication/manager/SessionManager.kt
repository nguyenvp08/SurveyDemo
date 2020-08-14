package com.andyho.sampleapplication.manager

import android.app.Application

object SessionManager {
    lateinit var context: Application

    var _token: String? = null

    var token: String?
        get() {
            if (_token.isNullOrEmpty()) {
                _token = SharePreferenceManager.getAccessToken()
            }
            return _token
        }
        set(value) {
            value?.also {accessToken ->
                SharePreferenceManager.saveAccessToken(accessToken)
            }
        }
}