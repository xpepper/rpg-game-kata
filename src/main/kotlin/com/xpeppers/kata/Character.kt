package com.xpeppers.kata

import kotlin.math.max
import kotlin.math.min

class Character(private val level: Int = 1, private val maxRangeAttack: Int = Int.MAX_VALUE) {
    companion object {
        const val MELEE_RANGE = 2
        const val RANGED_RANGE = 20

        fun meleeFighter(level: Int = 1) = Character(level, maxRangeAttack = MELEE_RANGE)
        fun rangedFighter(level: Int = 1) = Character(level, maxRangeAttack = RANGED_RANGE)
    }

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
            isFiveOrMoreLevelsBelow(character) -> damage / 2
            isFiveOrMoreLevelsAbove(character) -> damage + damage / 2
            else -> damage
        }
    }

    private fun isFiveOrMoreLevelsAbove(character: Character) = this.level - character.level >= 5

    private fun isFiveOrMoreLevelsBelow(character: Character) = character.level - this.level >= 5

    private fun isReachable(distance: Int) = distance <= maxRangeAttack
}