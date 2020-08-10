package com.example.aiboroid.api


import com.example.aiboroid.model.BaseResponse
import com.example.aiboroid.model.Devices
import com.example.aiboroid.model.SetModeArgument
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SettingService {
    @GET("devices")
    suspend fun getDevice(): Response<Devices>

    @GET("executions/{executionId}")
    suspend fun getExecution(@Path("executionId") executionId: String): Response<BaseResponse>

    @POST("devices/{deviceId}/capabilities/set_mode/execute")
    suspend fun setMode(@Path("deviceId") deviceId: String, @Body arg: SetModeArgument): Response<BaseResponse>
}