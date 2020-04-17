package com.xpeppers.kata

import org.junit.jupiter.api.Assertions.*
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

    @Test
    fun `when damage received exceeds current health, health becomes 0`() {
        val character = Character()

        character.receiveDamage(2000)

        assertEquals(0, character.health)
    }

    @Test
    fun `when damage received exceeds current health, the character dies`() {
        val character = Character()

        assertTrue(character.isAlive())

        character.receiveDamage(2000)

        assertFalse(character.isAlive())
    }
}
