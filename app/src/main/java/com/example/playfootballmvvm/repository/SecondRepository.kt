package com.example.playfootballmvvm.repository

import com.example.playfootballmvvm.retrofit.ApiService
import com.example.playfootballmvvm.retrofit.ApiService2
import kotlinx.coroutines.flow.flow

class SecondRepository(private val apiService2: ApiService2) {
    suspend fun getLeagueTopScores(str: String) = flow { emit(apiService2.getTopScores(str = str)) }
}