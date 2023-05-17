package com.test.meli.data.network

import com.google.gson.annotations.SerializedName

data class HttpError(
    @SerializedName("message") val errorMessage: String,
    @SerializedName("status") val errorCode: Int
)
