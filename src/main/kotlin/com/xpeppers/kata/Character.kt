package com.xpeppers.kata

import kotlin.math.max
import kotlin.math.min

class Character(private val level: Int) {
    var health: Int = 1000
        private set

    fun attack(character: Character, damage: Int) {
        if (this !== character) {
            val damageToDeal = computeDamageFor(character, damage)
            character.receiveDamage(damageToDeal)
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

    private fun computeDamageFor(character: Character, damage: Int): Int {
        if (character.level - this.level >= 5) {
            return damage / 2
        }
        if (this.level - character.level >= 5) {
            return damage + damage / 2
        }
        return damage
    }
}