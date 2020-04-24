package com.xpeppers.kata

import kotlin.math.max
import kotlin.math.min

class Character {
    var health: Int = 1000
        private set

    fun receiveDamage(damage: Int) {
        health = max(health - damage, 0)
    }

    fun isAlive(): Boolean {
        return health > 0
    }

    fun heal(health: Int) {
        this.health = min(this.health + health, 1000)
    }
}