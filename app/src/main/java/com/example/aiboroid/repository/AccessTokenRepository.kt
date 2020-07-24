package com.example.aiboroid.repository

import com.example.aiboroid.AiboroidApplication
import com.example.aiboroid.model.AccessToken
import java.util.*

class AccessTokenRepository {
    fun store(accessToken: String) {
        val storeAccessToken = AccessToken(UUID.randomUUID().toString(), accessToken)
        val dao = AiboroidApplication.database.accessTokenDao()
        dao.insertAccessToken(storeAccessToken)
    }
}