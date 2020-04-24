package com.xpeppers.kata

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RPGGameTest {

    @Test
    fun `a character can deal damage only to another character`() {
        val attacker = Character()
        val character = Character()
        val initialHealth = character.health
        val damage = 1

        attacker.attack(character, damage)

        assertEquals(initialHealth - damage, character.health)
    }

    @Test
    fun `a character cannot deal damage to itself`() {
        val attacker = Character()
        val initialHealth = attacker.health

        attacker.attack(attacker, 1)

        assertEquals(initialHealth, attacker.health)
    }

    @Test
    fun `when damage received exceeds current health, health becomes 0`() {
        val character = Character()

        Character(1).attack(character, 2000)

        assertEquals(0, character.health)
    }

    @Test
    fun `when damage received exceeds current health, the character dies`() {
        val character = Character()

        assertTrue(character.isAlive())

        Character().attack(character, 2000)

        assertFalse(character.isAlive())
    }

    @Test
    fun `a character can receive heal`() {
        val character = Character()
        Character().attack(character, 10)
        val currentHealth = character.health

        character.heal(1)

        assertEquals(currentHealth + 1, character.health)
    }

    @Test
    fun `healing cannot raise health above 1000`() {
        val character = Character()
        val maxHealth = 1000
        Character(1).attack(character, 5)

        character.heal(10)

        assertEquals(maxHealth, character.health)
    }

    @Test
    fun `dead characters cannot be healed`() {
        val character = Character()
        Character(1).attack(character, 1001)

        character.heal(20)

        assertEquals(0, character.health)
        assertFalse(character.isAlive())
    }

    @Test
    fun `when dealing damage, if the target is 5 or more levels above the attacker, damage is reduced by 50%`() {
        val attacker = Character(level = 1)
        val defender = Character(level = 6)
        val initialHealth = defender.health

        attacker.attack(defender, 10)

        assertEquals(initialHealth - 10/2, defender.health)
    }

    @Test
    fun `when dealing damage, if the target is 5 or more levels below the attacker, damage is increased by 50%`() {
        val attacker = Character(level = 6)
        val defender = Character(level = 1)
        val initialHealth = defender.health

        attacker.attack(defender, 10)

        assertEquals(initialHealth - (10 + 10/2), defender.health)

    }

    /*@Test
    fun `melee fighters can attack within a range of 2 meters`() {
        val meleeAttacker = Character(1, maxRangeAttack = 2)
        val meleeDefender = Character(1, maxRangeAttack = 2)
        val initialHealth = meleeDefender.health

        meleeAttacker.attack(meleeDefender, 5, distance = 1)

        assertEquals(initialHealth - 5, meleeDefender.health)

    }*/
}
