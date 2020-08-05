package com.example.aiboroid.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// TODO: Rename me
class TmpName(accessToken: String) {

    private var retrofit: Retrofit

    private fun createOkHttpClientInterceptor(accessToken: String) = Interceptor { chain ->
        val request = chain.request().newBuilder().apply {
            addHeader(
                "Authorization",
                "Bearer $accessToken"
            )
        }.build()
        chain.proceed(request)
    }

    init {
        val okHttpClientInterceptor = createOkHttpClientInterceptor(accessToken)
        val httpBuilder = OkHttpClient.Builder().apply {
            addInterceptor(okHttpClientInterceptor)
            connectTimeout(5, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
        }

        retrofit = Retrofit.Builder()
            .baseUrl("https://public.api.aibo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpBuilder.build())
            .build()
    }

    val settingService: SettingService by lazy {
        retrofit.create(SettingService::class.java)
    }
    
    val actionService: ActionService by lazy {
        retrofit.create(ActionService::class.java)
    }
}
