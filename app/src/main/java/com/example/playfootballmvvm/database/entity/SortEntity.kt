package com.example.playfootballmvvm.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SortEntity(
    @PrimaryKey
    val id: Int = 0,
    var name: String,
    var logo: String,
    var shortName: String,
    var rank: Int,
    var abbreviation: String
)