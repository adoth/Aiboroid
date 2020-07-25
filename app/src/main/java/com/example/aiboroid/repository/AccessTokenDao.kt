package com.example.aiboroid.repository

import androidx.room.*
import com.example.aiboroid.model.AccessToken

@Dao
interface AccessTokenDao {
    @Query("SELECT * FROM accessToken WHERE uuid = :uuid")
    suspend fun getAccessToken(uuid: String) : AccessToken?

    @Query("SELECT * FROM accessToken")
    suspend fun getAll() : List<AccessToken>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccessToken(accessToken: AccessToken)
}