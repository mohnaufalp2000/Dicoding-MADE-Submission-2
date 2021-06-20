package com.naufal.techmedia.core.data.source.remote.network

import com.naufal.techmedia.core.data.source.remote.response.NewsResponse
import com.naufal.techmedia.core.util.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    suspend fun getListNews(
            @Query("country") country : String = Constant.COUNTRY,
            @Query("category") category: String = Constant.CATEGORY,
            @Query("apiKey") apiKey : String = Constant.API_KEY
    ) : NewsResponse

}