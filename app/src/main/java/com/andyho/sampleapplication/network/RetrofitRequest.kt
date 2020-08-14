package com.andyho.sampleapplication.network

import com.andyho.sampleapplication.manager.SessionManager
import com.andyho.sampleapplication.model.Survey
import com.andyho.sampleapplication.model.response.AccessTokenResponse
import com.andyho.sampleapplication.network.interceptor.AuthenticationInterceptor
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

object RetrofitRequest {
    private const val BASE_URL = "https://nimble-survey-api.herokuapp.com"
    private const val DEFAULT_GRANT_TYPE = "password"
    private const val DEFAULT_USERNAME = "carlos@nimbl3.com"
    private const val DEFAULT_PASSWORD = "antikera"

    private val httpClient = OkHttpClient.Builder()
        .authenticator { route, response ->
            val newToken = getApiResource()
                .getAccessToken(DEFAULT_GRANT_TYPE, DEFAULT_USERNAME, DEFAULT_PASSWORD).execute().body()?.access_token
            if (newToken != null) {
                SessionManager.token = newToken
            } else {
                // throw error cause cant' refresh token
                throw RuntimeException("exception can't refresh token")
            }

            // Add new header to rejected request and retry it
            response.request().newBuilder()
                .header(
                    "Authorization",
                    "Bearer ${newToken}"
                )
                .build()
        }
        .addInterceptor(AuthenticationInterceptor)
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    fun getApiResource(): ApiResource {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
        return retrofit.create(ApiResource::class.java)
    }

    interface ApiResource {

        @FormUrlEncoded
        @POST("/oauth/token")
        fun getAccessToken(@Field("grant_type") grantType: String,
                           @Field("username") userName: String,
                           @Field("password") password: String): Call<AccessTokenResponse>

        @Headers("Content-type: application/json")
        @GET("/surveys.json")
        fun getSurveys(@Query("page") page: Int, @Query("per_page") pageSize: Int): Single<List<Survey>>
    }
}