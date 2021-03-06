package com.example.aiboroid.di

import com.example.aiboroid.api.ApiService
import com.example.aiboroid.repository.AccessTokenRepository
import com.example.aiboroid.viewmodel.AccessTokenSettingViewModel
import com.example.aiboroid.viewmodel.ActionApiViewModel
import com.example.aiboroid.viewmodel.DevicesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { AccessTokenSettingViewModel() }
    viewModel { DevicesViewModel(get()) }
    viewModel { (accessToken: String, deviceId: String) -> ActionApiViewModel(accessToken, deviceId) }
}

val repositoryModule = module {
    single { AccessTokenRepository() }
}

val networkModule = module {
    single { ApiService(get()) }
}

// TODO: Rename me
val appModule = listOf(viewModelModule, repositoryModule, networkModule)