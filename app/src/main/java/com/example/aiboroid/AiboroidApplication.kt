package com.example.aiboroid

import android.app.Application
import androidx.room.Room
import com.example.aiboroid.api.SettingService
import com.example.aiboroid.repository.AppDatabase
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AiboroidApplication : Application() {
    companion object {
        lateinit var database: AppDatabase
        lateinit var retrofit: Retrofit
        lateinit var settingService: SettingService
    }

    override fun onCreate() {
        super.onCreate()

        // TODO: find the right init location.
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "accessToken"
        ).build()

        // TODO: find the right init location.
        val okHttpClientInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder().apply {
                addHeader("Authorization", "Bearer ")
            }.build()
            chain.proceed(request)
        }

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

        settingService = retrofit.create(SettingService::class.java)
    }

}