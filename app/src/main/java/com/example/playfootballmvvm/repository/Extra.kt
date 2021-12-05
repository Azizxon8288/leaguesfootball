package com.example.playfootballmvvm.repository

import com.example.playfootballmvvm.database.entity.League

object Extra {
    private var league: League? = null

    fun setLeague1(league: League) {
        Extra.league = league
    }

    fun getLeague1(): League? {
        return league
    }

}