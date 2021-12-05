package com.example.playfootballmvvm.model.topscores

data class Player(
    val countryOfBirth: String,
    val dateOfBirth: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val lastUpdated: String,
    val name: String,
    val nationality: String,
    val position: String,
    val shirtNumber: Any
)