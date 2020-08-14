package com.andyho.sampleapplication.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Survey(val id: String, val title: String, val description: String,
                    @SerializedName("cover_image_url") val coverImageUrl: String,
                    @SerializedName("questions") val questions: List<Question>) : Serializable