package com.test.meli.data.remote

import com.test.meli.data.remote.response.LookFor
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServicesInterface {

    @GET("sites/MLA/search")
    fun getProductsBySearch(@Query("q") query: String): Call<LookFor>?
}