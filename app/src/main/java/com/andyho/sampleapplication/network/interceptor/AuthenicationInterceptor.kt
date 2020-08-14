package com.andyho.sampleapplication.network.interceptor

import com.andyho.sampleapplication.manager.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

object AuthenticationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!SessionManager.token.isNullOrEmpty()) {
            val request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${SessionManager.token}")
                .build()

            return chain.proceed(request)
        }

        return chain.proceed(chain.request())
    }
}