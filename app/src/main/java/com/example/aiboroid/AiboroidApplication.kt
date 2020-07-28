package com.example.aiboroid

import android.app.Application
import androidx.room.Room
import com.example.aiboroid.di.appModule
import com.example.aiboroid.repository.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AiboroidApplication : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AiboroidApplication)
            modules(appModule)
        }

        // TODO: find the right init location.
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "accessToken"
        ).build()
    }
}