package com.example.playfootballmvvm.repository

import com.example.playfootballmvvm.retrofit.ApiService
import kotlinx.coroutines.flow.flow

class TeamRepository(var apiSer: ApiService) {
    suspend fun getLeagues() = flow { emit(apiSer.getLeague()) }
    suspend fun getTeamsByLeague(id: String) = flow { emit(apiSer.getLeagueAll(id)) }


}