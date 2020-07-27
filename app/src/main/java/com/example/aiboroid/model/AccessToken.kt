package com.example.aiboroid.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accessToken")
data class AccessToken(
    @PrimaryKey val uuid: String,
    @ColumnInfo(name = "access_token") val accessToken: String?
)