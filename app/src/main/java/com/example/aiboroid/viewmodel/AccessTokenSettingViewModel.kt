package com.example.aiboroid.viewmodel

import androidx.lifecycle.ViewModel
import com.example.aiboroid.repository.AccessToken.AccessTokenRepository

class AccessTokenSettingViewModel : ViewModel() {

    private val accessTokenRepository = AccessTokenRepository()

    fun onResisterButtonClick(accessToken: String) {
        accessTokenRepository.storeAccessToken(accessToken)
    }
}
