package com.example.playfootballmvvm.repository

import com.example.playfootballmvvm.retrofit.ApiService
import kotlinx.coroutines.flow.flow

class SortRepository(private val apiService: ApiService) {
    suspend fun getLeague() = flow { emit(apiService.getLeague()) }
    suspend fun getTeamsByLeague(id: String) = flow { emit(apiService.getLeagueAll(id)) }


}