package com.vimee.apps.cardStackInternals

import android.view.animation.Interpolator

interface AnimationSetting {
    val direction: Direction?
    val duration: Int
    val interpolator: Interpolator?
}