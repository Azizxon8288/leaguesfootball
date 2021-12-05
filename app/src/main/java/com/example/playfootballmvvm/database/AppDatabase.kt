package com.example.playfootballmvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.playfootballmvvm.database.dao.SortDao
import com.example.playfootballmvvm.database.entity.SortEntity

abstract class AppDatabase : RoomDatabase() {

    abstract fun sortDao(): SortDao

    companion object {
        private var appDatabase: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return appDatabase!!
        }
    }

}