package com.vimee.apps.cardStackInternals

import java.util.*

enum class Direction {
    Left, Right, Top, Bottom;

    companion object {
        val HORIZONTAL = listOf(Left, Right)
        val VERTICAL = listOf(Top, Bottom)
        val FREEDOM: List<Direction> =
            listOf(*com.vimee.apps.cardStackInternals.Direction.values())
    }
}