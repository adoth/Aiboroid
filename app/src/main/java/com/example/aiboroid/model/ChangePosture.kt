package com.example.aiboroid.model

class ChangePosture(override val displayName: String, changePostureType: ChangePostureType) : SingleParameter {
    enum class ChangePostureType {
        back,
        crouch,
        down,
        down_and_lengthen_behind,
        down_and_shorten_behind,
        sit_and_raise_both_hands,
        sit,
        sleep,
        stand,
        stand_straight
    }

    override val parameter: String = changePostureType.toString()

}