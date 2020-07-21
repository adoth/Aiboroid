package com.example.aiboroid.repository.AccessToken

import com.example.aiboroid.model.AccessToken

class AccessTokenRepository {
    private lateinit var accessToken: AccessToken
    fun fetchAccessToken(): AccessToken {
        return accessToken
    }
    fun storeAccessToken(accessToken: String) {
        this.accessToken = AccessToken(accessToken)
    }
}