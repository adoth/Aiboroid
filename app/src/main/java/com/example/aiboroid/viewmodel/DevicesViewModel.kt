package com.example.aiboroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiboroid.api.TmpName
import com.example.aiboroid.model.AccessToken
import com.example.aiboroid.model.Device
import com.example.aiboroid.repository.AccessTokenRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class DevicesViewModel : ViewModel() {
    private val accessTokenRepository = AccessTokenRepository()

    val accessToken: MutableLiveData<AccessToken> by lazy {
        MutableLiveData<AccessToken>()
    }

    val devices : MutableLiveData<List<Device>> by lazy {
        MutableLiveData<List<Device>>()
    }

    fun getAccessToken() {
        viewModelScope.launch {
            accessToken.value = accessTokenRepository.get()
        }
    }

    fun getDeviceApi() {
        viewModelScope.launch {
            try {
                // TODO injection
                val settingService = TmpName(accessToken.value?.accessToken.toString()).settingService
                val response = settingService.getDevice()
                devices.value = response.body()?.devices
            } catch (e: Exception) {
                // TODO: Error handling
            }
        }
    }
}