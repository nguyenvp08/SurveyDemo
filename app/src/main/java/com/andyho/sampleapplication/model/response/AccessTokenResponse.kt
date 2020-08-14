package com.andyho.sampleapplication.model.response

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(@SerializedName("access_token") val access_token: String, @SerializedName("token_type") val token_type: String, @SerializedName("expires_in") val expires_in: Long, @SerializedName("created_at") val created_at: Long)