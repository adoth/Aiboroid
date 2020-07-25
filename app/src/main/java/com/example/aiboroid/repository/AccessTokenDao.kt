package com.example.aiboroid.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.aiboroid.model.AccessToken

@Dao
interface AccessTokenDao {
    @Query("SELECT * FROM accessToken WHERE uuid = :uuid")
    suspend fun getAccessToken(uuid: String) : AccessToken?

    @Query("SELECT * FROM accessToken")
    suspend fun getAll() : List<AccessToken>

    @Insert
    suspend fun insertAccessToken(accessToken: AccessToken)
}