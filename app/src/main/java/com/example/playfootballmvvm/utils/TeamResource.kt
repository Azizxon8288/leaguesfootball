package com.example.playfootballmvvm.utils

import com.example.playfootballmvvm.model.SortLeague

sealed class TeamResource {
    object Loading : TeamResource()
    data class Error(var message: String) : TeamResource()
    data class Success(var body: SortLeague) : TeamResource()
}