package com.example.aiboroid.repository

import com.example.aiboroid.AiboroidApplication
import com.example.aiboroid.model.AccessToken

class AccessTokenRepository {
    suspend fun store(accessToken: String) {
        // TODO: for now id
        val storeAccessToken = AccessToken("1", accessToken)
        val dao = AiboroidApplication.database.accessTokenDao()
        dao.insertAccessToken(storeAccessToken)
    }

    suspend fun get() : AccessToken? {
        val dao = AiboroidApplication.database.accessTokenDao()
        return dao.getAccessToken("1")
    }

    suspend fun delete() {
        val dao = AiboroidApplication.database.accessTokenDao()
        dao.delete(AccessToken("1", ""))
    }
}