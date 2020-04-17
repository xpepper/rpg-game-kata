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

    @Test
    fun `a character can receive heal`() {
        val character = Character()
        character.receiveDamage(10)
        val currentHealth = character.health

        character.heal(1)

        assertEquals(currentHealth + 1, character.health)
    }

    @Test
    fun `healing cannot raise health above 1000`() {
        val character = Character()
        val maxHealth = 1000

        character.receiveDamage(5)
        character.heal(10)

        assertEquals(maxHealth, character.health)
    }
}
