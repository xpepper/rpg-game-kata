package com.xpeppers.kata

import kotlin.math.max

class Character {
    var health: Int = 1000

    fun receiveDamage(damage: Int) {
        health = max(health - damage, 0)
    }

    fun isAlive(): Boolean {
        return health > 0
    }
}