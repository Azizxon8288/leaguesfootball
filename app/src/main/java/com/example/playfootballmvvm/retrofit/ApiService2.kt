package com.example.playfootballmvvm.retrofit

import com.example.playfootballmvvm.model.SortLeague
import com.example.playfootballmvvm.model.data.HomeData
import com.example.playfootballmvvm.model.topscores.TopScores
import retrofit2.Response
import retrofit2.http.*

interface ApiService2 {
    @Headers("X-Auth-Token: 82a72eeceb544f8ba1798ce8475eb808 ")
    @GET("competitions/{str}/scorers?limit=15")
    suspend fun getTopScores(
        @Path("str") str: String,
    ): Response<TopScores>


}