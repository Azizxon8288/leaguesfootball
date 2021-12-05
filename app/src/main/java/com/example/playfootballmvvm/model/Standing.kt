package com.example.playfootballmvvm.model

import java.io.Serializable

data class Standing(
    val note: Note,
    val stats: List<Stat>,
    val team: Team
):Serializable