package com.example.aiboroid.model

class ChangePosture(override val displayName: String, changePostureType: ChangePostureType) : SingleParameter {
    enum class ChangePostureType {
        BACK,
        CROUCH,
        DOWN,
        DOWN_AND_LENGTHEN_BEHIND,
        DOWN_AND_SHORTEN_BEHIND,
        SIT_AND_RAISE_BOTH_HANDS,
        SIT,
        SLEEP,
        STAND,
        STAND_STRAIGHT
    }

    override val parameter: String = changePostureType.toString().toLowerCase()

}