package com.example.aiboroid.repository

import com.example.aiboroid.AiboroidApplication
import com.example.aiboroid.model.AccessToken
import java.util.*

class AccessTokenRepository {
    suspend fun store(accessToken: String) {
        // TODO: for now id
        val storeAccessToken = AccessToken(UUID.randomUUID().toString(), accessToken)
        val dao = AiboroidApplication.database.accessTokenDao()
        dao.insertAccessToken(storeAccessToken)
    }

    suspend fun get() : String? {
        val dao = AiboroidApplication.database.accessTokenDao()
        val accessToken = dao.getAccessToken(UUID.randomUUID().toString())
        return accessToken?.accessToken
    }

    suspend fun findAll(): List<AccessToken> {
        val dao = AiboroidApplication.database.accessTokenDao()
        return dao.getAll()
    }
}