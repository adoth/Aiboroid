package com.example.aiboroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiboroid.api.TmpName
import com.example.aiboroid.model.Device
import com.example.aiboroid.repository.AccessTokenRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.lang.Exception

class DevicesViewModel(private val accessTokenRepository: AccessTokenRepository) : ViewModel() {
    var accessToken = MutableLiveData<String>()
    var accessTokenState = MutableLiveData<AccessTokenState>()

    val devices : MutableLiveData<List<Device>> by lazy {
        MutableLiveData<List<Device>>()
    }

    enum class AccessTokenState {
        NOT_YET,
        SAVED,
        INVALID,
        EXCEED_RATE_LIMIT,
        SERVER_ERROR
    }

    init {
        viewModelScope.launch {
            val accessTokenStore = accessTokenRepository.get()
            if (accessTokenStore == null) {
                accessToken.value = null
                accessTokenState.value = AccessTokenState.NOT_YET
            } else {
                accessToken.value = accessTokenStore.accessToken.toString()
                accessTokenState.value = AccessTokenState.SAVED
            }
        }
    }

    fun getDeviceApi() {
        val value = accessToken.value ?: return
        viewModelScope.launch {
            val settingService = TmpName(value).settingService
            val response = settingService.getDevice()
            if (response.isSuccessful)
                devices.value = response.body()?.devices
            else {
                when(response.code()) {
                    401 -> accessTokenState.value = AccessTokenState.INVALID
                    429 -> accessTokenState.value = AccessTokenState.EXCEED_RATE_LIMIT
                    500, 503 -> accessTokenState.value = AccessTokenState.SERVER_ERROR
                    else -> accessTokenState.value = AccessTokenState.INVALID
                }
            }
        }
    }

    fun deleteAccessToken() {
        viewModelScope.launch {
            accessTokenRepository.delete()
        }
    }

    private fun tmp() {}
}