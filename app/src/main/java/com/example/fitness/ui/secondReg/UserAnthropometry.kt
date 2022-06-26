package com.example.fitness.ui.secondReg

import com.example.fitness.R

//hint for tv
sealed class UserAnthropometry(val iconId: Int = 0, val unitId: Int = 0, val hintId: Int = 0) {
    object Weight: UserAnthropometry(R.drawable.ic_weight, R.string.kg, R.string.yourWeight)
    object Height: UserAnthropometry(R.drawable.ic_height, R.string.cm, R.string.yourHeight)
}
