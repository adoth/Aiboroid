package com.example.aiboroid.model

data class BaseResponse(
    val executionId: String,
    val status: String,
    val result: List<Any>
)