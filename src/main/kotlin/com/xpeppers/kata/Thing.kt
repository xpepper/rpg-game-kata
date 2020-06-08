package com.xpeppers.kata

import kotlin.math.max

class Thing(_health: Int) {

    var health: Int = _health
    private set

    fun receiveDamage(damage: Int) {
        health = max(health - damage, 0)
    }

    fun isDestroyed(): Boolean = health <= 0
}