package com.xpeppers.kata

import kotlin.math.max
import kotlin.math.min

class Character(private val level: Int) {
    var health: Int = 1000
        private set

    fun attack(character: Character, damage: Int) {
        if (this !== character) {
            if (character.level - this.level >= 5) {
                character.receiveDamage(damage / 2)
            } else {
                character.receiveDamage(damage)
            }
        }
    }

    fun isAlive(): Boolean {
        return health > 0
    }

    fun heal(health: Int) {
        if (isAlive()) {
            healYourself(health)
        }
    }

    private fun receiveDamage(damage: Int) {
        health = max(health - damage, 0)
    }

    private fun healYourself(health: Int) {
        this.health = min(this.health + health, 1000)
    }
}