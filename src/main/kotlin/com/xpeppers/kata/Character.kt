package com.xpeppers.kata

class Character {
    var health: Int = 1000

    fun receiveDamage(damage: Int) {
        if (damage < health) {
            health -= damage
        } else {
            health = 0
        }
    }

    fun isAlive(): Boolean {
        return health > 0
    }
}