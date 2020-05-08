package com.xpeppers.kata

import kotlin.math.max
import kotlin.math.min

class Character(private val level: Int = 1, private val maxRangeAttack: Int = Int.MAX_VALUE) {
    var health: Int = 1000
        private set

    fun attack(character: Character, damage: Int, distance: Int = 0) {
        if (this !== character) {
            if (isReachable(distance)) {
                val damageToDeal = computeDamageFor(character, damage)
                character.receiveDamage(damageToDeal)
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

    private fun computeDamageFor(character: Character, damage: Int): Int {
        return when {
            isWeakerThan(character) -> damage / 2
            isStrongerThan(character) -> damage + damage / 2
            else -> damage
        }
    }

    private fun isStrongerThan(character: Character) = this.level - character.level >= 5

    private fun isWeakerThan(character: Character) = character.level - this.level >= 5

    private fun isReachable(distance: Int) = distance <= maxRangeAttack
}