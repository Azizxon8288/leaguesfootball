package com.example.playfootballmvvm.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.playfootballmvvm.database.entity.SortEntity
import com.example.playfootballmvvm.model.SortLeague

@Dao
interface SortDao {
    @Insert
    fun addSort(sortLeague: SortLeague)

    @Query("select * from sortentity")
    fun getAllLeague(): List<SortEntity>
}
