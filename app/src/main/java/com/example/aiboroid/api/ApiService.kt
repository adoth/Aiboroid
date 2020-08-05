package com.example.aiboroid.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiService(accessToken: String) {

    private var retrofit: Retrofit

    private fun createOkHttpClientInterceptor(accessToken: String): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val original = chain.request()

                //header
                val request = original.newBuilder()
                    .header("Authorization", "Bearer $accessToken")
                    .method(original.method(), original.body())
                    .build()

                return@Interceptor chain.proceed(request)
            })
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)

        // log interceptor
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(loggingInterceptor)

        return httpClient
    }

    init {
        val httpClient = createOkHttpClientInterceptor(accessToken)

        retrofit = Retrofit.Builder()
            .baseUrl("https://public.api.aibo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    val settingService: SettingService by lazy {
        retrofit.create(SettingService::class.java)
    }

    val actionService: ActionService by lazy {
        retrofit.create(ActionService::class.java)
    }
}
