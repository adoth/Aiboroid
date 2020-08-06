package com.example.aiboroid.model

import java.util.*

data class BaseResponse(
    val executionId: String,
    val status: String,
    val result: Objects?
)