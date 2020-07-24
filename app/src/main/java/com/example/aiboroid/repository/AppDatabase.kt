package com.example.aiboroid.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aiboroid.model.AccessToken

@Database(entities = [AccessToken::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accessTokenDao(): AccessTokenDao
}