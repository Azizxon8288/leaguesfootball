package com.example.playfootballmvvm.model.data

data class HomeData(
    val competition: Competition,
    val filters: Filters,
    val season: Season,
    val standings: List<Standing>
)