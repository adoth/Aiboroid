package com.example.aiboroid.api


import com.example.aiboroid.model.Devices
import retrofit2.Response
import retrofit2.http.GET

interface SettingService {
    @GET("devices")
    suspend fun getDevice(): Response<Devices>
}