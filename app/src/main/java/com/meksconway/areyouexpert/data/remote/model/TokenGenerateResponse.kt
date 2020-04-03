package com.meksconway.areyouexpert.data.remote.model

import com.google.gson.annotations.SerializedName

data class TokenGenerateResponse(
    @SerializedName("response_code")
    val responseCode: Int?,
    @SerializedName("response_message")
    val responseMessage: String?,
    @SerializedName("token")
    val generatedToken: String?
)