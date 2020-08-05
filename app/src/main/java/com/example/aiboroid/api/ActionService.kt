package com.example.aiboroid.api

import com.example.aiboroid.model.BaseResponse
import com.example.aiboroid.model.FinalPostureArgument
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ActionService {
    @POST("{deviceId}/capabilities/change_posture/execute")
    fun changePostureDown(@Path("deviceId") deviceId: String, @Body arg: FinalPostureArgument): Call<BaseResponse>
}