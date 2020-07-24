package com.example.aiboroid.viewmodel

import androidx.lifecycle.ViewModel
import com.example.aiboroid.repository.AccessTokenRepository

class AccessTokenSettingViewModel() : ViewModel() {

    private val accessTokenRepository = AccessTokenRepository()

    fun storeAccessToken(accessToken: String) {
        accessTokenRepository.store(accessToken)
    }
}
