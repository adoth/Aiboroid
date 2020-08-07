package com.example.aiboroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aiboroid.api.ApiService
import com.example.aiboroid.model.FinalPosture
import com.example.aiboroid.model.FinalPostureArgument
import kotlinx.coroutines.delay
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
                checkExecute(response.body()!!.executionId)
            }
        }
    }

    private fun checkExecute(executionId: String) {
        viewModelScope.launch {
            var cnt = 0
            loop@ while (true) {
                delay(3000)
                val response = actionService.getExecution(executionId)
                if (response.isSuccessful) {
                    when (response.body()!!.status) {
                        "SUCCEEDED" -> {
                            executionState.value = ExecutionState.SUCCEEDED
                            break@loop
                        }
                        "FAILED" -> {
                            executionState.value = ExecutionState.FAILED
                            break@loop
                        }
                        else -> cnt++
                    }
                }
                // TODO: fix fxxk timeout
                if (cnt == 5) {
                    executionState.value = ExecutionState.NONE
                    break@loop
                }
            }
        }
    }
}