package com.example.playfootballmvvm.retrofit

import com.example.playfootballmvvm.model.SortLeague
import com.example.playfootballmvvm.model.data.HomeData
import com.example.playfootballmvvm.model.topscores.TopScores
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("leagues/esp.1/standings?season=2021&sort=asc")
    suspend fun getLeague(): Response<SortLeague>

    @GET("leagues/{id}/standings?season=2021&sort=asc")
    suspend fun getLeagueAll(@Path("id") id: String): Response<SortLeague>

    @Headers("X-Auth-Token: 82a72eeceb544f8ba1798ce8475eb808 ")
    @GET()
    suspend fun getTopScores(
        @Url url: String = "https://api.football-data.org/v2/competitions/{str}/scorers?limit=15",
        @Path("str") str: String,
    ): Response<TopScores>


}