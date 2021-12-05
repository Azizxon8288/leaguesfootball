package com.example.playfootballmvvm.utils

import com.example.playfootballmvvm.model.SortLeague
import com.example.playfootballmvvm.model.topscores.TopScores

sealed class TopScoresResource {
    object Loading : TopScoresResource()
    data class Error(var message: String) : TopScoresResource()
    data class Success(var body: TopScores) : TopScoresResource()
}