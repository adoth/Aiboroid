package com.example.aiboroid.api

import com.example.aiboroid.model.BaseResponse
import com.example.aiboroid.model.FinalPostureArgument
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ActionService {
    @GET("executions/{executionId}")
    suspend fun getExecution(@Path("executionId") executionId: String): Response<BaseResponse>

    @POST("devices/{deviceId}/capabilities/change_posture/execute")
    suspend fun changePostureDown(@Path("deviceId") deviceId: String, @Body arg: FinalPostureArgument): Response<BaseResponse>
}