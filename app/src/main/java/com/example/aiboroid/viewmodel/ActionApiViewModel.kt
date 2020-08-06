package com.example.aiboroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiboroid.api.ApiService
import com.example.aiboroid.model.FinalPosture
import com.example.aiboroid.model.FinalPostureArgument
import kotlinx.coroutines.launch

class ActionApiViewModel(accessToken: String, private val deviceId: String) :
    ViewModel() {

    private val actionService = ApiService(accessToken).actionService

    fun call() {
        viewModelScope.launch {
            val response = actionService.changePostureDown(
                deviceId, FinalPostureArgument(
                    FinalPosture("down")
                )
            )
        }
    }
}