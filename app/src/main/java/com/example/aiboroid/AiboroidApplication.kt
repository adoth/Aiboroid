package com.example.aiboroid

import android.app.Application
import androidx.room.Room
import com.example.aiboroid.repository.AppDatabase

class AiboroidApplication : Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        // TODO: find the right init location.
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "accessToken"
        ).build()
    }

}