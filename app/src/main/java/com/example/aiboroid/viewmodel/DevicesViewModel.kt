package com.example.aiboroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiboroid.model.AccessToken
import com.example.aiboroid.repository.AccessTokenRepository
import kotlinx.coroutines.launch

class DevicesViewModel : ViewModel() {
    private val accessTokenRepository = AccessTokenRepository()

    val accessToken: MutableLiveData<AccessToken> by lazy {
        MutableLiveData<AccessToken>()
    }

    fun getAccessToken() {
        viewModelScope.launch {
            accessTokenRepository.findAll()
            Thread.sleep(5000)
            accessToken.value = AccessToken("1", "dadada")
        }
    }
}