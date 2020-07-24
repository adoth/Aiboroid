package com.example.aiboroid.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.aiboroid.model.AccessToken

@Dao
interface AccessTokenDao {
    @Query("SELECT * FROM accessToken WHERE uuid = :uuid")
    fun getAccessToken(uuid: String) : AccessToken

    @Insert
    fun insertAccessToken(accessToken: AccessToken)
}