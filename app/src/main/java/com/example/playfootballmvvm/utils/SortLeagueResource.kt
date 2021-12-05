package com.example.playfootballmvvm.utils

import com.example.playfootballmvvm.model.SortLeague


sealed class SortLeagueResource {

    object Loading : SortLeagueResource()

    data class Success(val list: SortLeague?) : SortLeagueResource()

    data class Error(val message: String) : SortLeagueResource()
}