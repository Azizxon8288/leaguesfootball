package com.example.playfootballmvvm.database.entity

import java.io.Serializable

data class League(
    val id: String,
    var name: String,
    var image: String,
    var ab: String,
    var tDivision: String
) : Serializable