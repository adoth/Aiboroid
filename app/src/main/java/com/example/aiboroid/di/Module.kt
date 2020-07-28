package com.example.aiboroid.di

import com.example.aiboroid.api.TmpName
import com.example.aiboroid.viewmodel.AccessTokenSettingViewModel
import com.example.aiboroid.viewmodel.DevicesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { AccessTokenSettingViewModel() }
    viewModel { DevicesViewModel() }
}

val networkModule = module {
    single { TmpName(get()) }
}

// TODO: Rename me
val appModule = listOf(viewModelModule, networkModule)