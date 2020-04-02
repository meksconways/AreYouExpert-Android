package com.meksconway.areyouexpert.data.triviamodel

import com.google.gson.annotations.SerializedName

data class TokenGenerateResponse(
    @SerializedName("response_code")
    val responseCode: Int?,
    @SerializedName("response_message")
    val responseMessage: String?,
    @SerializedName("token")
    val generatedToken: String?
)