package com.example.playfootballmvvm.model.topscores

data class TopScores(
    val competition: Competition,
    val count: Int,
    val filters: Filters,
    val scorers: List<Scorer>,
    val season: Season
)