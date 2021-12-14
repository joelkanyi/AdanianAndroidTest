package com.kanyideveloper.adanianandroidtest.data.remote

import com.kanyideveloper.adanianandroidtest.data.remote.dto.ApiResponse
import com.kanyideveloper.adanianandroidtest.data.util.Constants.KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("?key=$KEY")
    suspend fun searchImages(@Query("q") query: String?): ApiResponse
}