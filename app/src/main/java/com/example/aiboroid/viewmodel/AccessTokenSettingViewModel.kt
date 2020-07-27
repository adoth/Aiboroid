package com.example.aiboroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiboroid.repository.AccessTokenRepository
import kotlinx.coroutines.launch

class AccessTokenSettingViewModel() : ViewModel() {

    private val accessTokenRepository = AccessTokenRepository()

    fun storeAccessToken(accessToken: String) {
        viewModelScope.launch {
            accessTokenRepository.store(accessToken)
        }
    }
}
