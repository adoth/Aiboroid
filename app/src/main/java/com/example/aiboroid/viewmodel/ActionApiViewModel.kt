package com.example.aiboroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiboroid.api.ApiService
import com.example.aiboroid.model.FinalPosture
import com.example.aiboroid.model.FinalPostureArgument
import kotlinx.coroutines.launch

class ActionApiViewModel(accessToken: String, private val deviceId: String) :
    ViewModel() {

    var executionState = MutableLiveData<ExecutionState>()

    enum class ExecutionState {
        NONE,
        SUCCEEDED,
        FAILED
    }

    private val actionService = ApiService(accessToken).actionService

    fun call() {
        viewModelScope.launch {
            val response = actionService.changePostureDown(
                deviceId, FinalPostureArgument(
                    FinalPosture("down")
                )
            )
            if (response.isSuccessful) {
                // TODO: retry until succeeded
                checkExecute(response.body()!!.executionId)
            }
        }
    }

    private fun checkExecute(executionId: String) {
        viewModelScope.launch {
            val response = actionService.getExecution(executionId)
            if (response.isSuccessful) {
                executionState.value = when (response.body()!!.status) {
                    "SUCCEEDED" -> ExecutionState.SUCCEEDED
                    "FAILED" -> ExecutionState.FAILED
                    else -> ExecutionState.NONE
                }
            }
        }
    }
}