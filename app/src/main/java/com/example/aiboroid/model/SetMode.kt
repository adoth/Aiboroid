package com.example.aiboroid.model

import java.lang.IllegalArgumentException

class SetMode(override val displayName: String, setModeType: SetModeType) : SingleParameter {
    enum class SetModeType {
        NORMAL,
        DEVELOPMENT
    }
    override val parameter = setModeType.toString()
}