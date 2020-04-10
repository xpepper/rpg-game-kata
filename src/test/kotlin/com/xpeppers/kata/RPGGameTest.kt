package com.xpeppers.kata

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RPGGameTest {

    @Test
    fun `when character receive damage its health decrease by that damage quantity`() {
        val character = Character()
        val initialHealth = character.health
        val damage = 1

        character.receiveDamage(damage)

        assertEquals(initialHealth - damage, character.health)
    }
}
