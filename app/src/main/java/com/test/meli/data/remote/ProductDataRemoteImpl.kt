package com.test.meli.data.remote

import android.util.Log
import com.test.meli.data.remote.response.LookFor
import com.test.meli.data.remote.sources.ProductDataRemoteSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProductDataRemoteImpl @Inject constructor(private val retrofitServicesInterface: RetrofitServicesInterface) :
    ProductDataRemoteSource {

    override fun getProductsBySearch() {
        val call: Call<LookFor>? =
            retrofitServicesInterface.getProductsBySearch("Motorola%20G6#json")

        call?.enqueue(object : Callback<LookFor> {
            override fun onFailure(call: Call<LookFor>, t: Throwable) {
                Log.d("Error", "Error")
            }

            override fun onResponse(call: Call<LookFor>, response: Response<LookFor>) {
                Log.d("Success", "Success")
            }
        })
    }
}