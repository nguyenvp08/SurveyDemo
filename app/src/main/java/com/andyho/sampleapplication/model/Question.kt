package com.andyho.sampleapplication.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Question(val id: String, val text: String, val pick: String,
                    @SerializedName("display_type") val displayType: String,
                    @SerializedName("display_order") val displayOrder: Int,
                    val answers: List<Answer>) : Serializable
