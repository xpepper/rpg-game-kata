package com.xpeppers.kata

import com.xpeppers.kata.Character.Companion.MAX_HEALTH
import com.xpeppers.kata.Character.Companion.MELEE_RANGE
import com.xpeppers.kata.Character.Companion.RANGED_RANGE
import com.xpeppers.kata.Character.Companion.meleeFighter
import com.xpeppers.kata.Character.Companion.rangedFighter
import com.xpeppers.kata.Faction.*
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
    fun `a character can heal itself`() {
        val character = Character()
        Character().attack(character, 10)
        val currentHealth = character.health

        character.heal(1)

        assertEquals(currentHealth + 1, character.health)
    }

    @Test
    fun `healing cannot raise health above its maximum value`() {
        val character = Character()
        Character(1).attack(character, 5)

        character.heal(10)

        assertEquals(MAX_HEALTH, character.health)
    }

    @Test
    fun `dead characters cannot heal themselves`() {
        val character = Character()
        kill(character)

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

        assertEquals(initialHealth - 10 / 2, defender.health)
    }

    @Test
    fun `when dealing damage, if the target is 5 or more levels below the attacker, damage is increased by 50%`() {
        val attacker = Character(level = 6)
        val defender = Character(level = 1)
        val initialHealth = defender.health

        attacker.attack(defender, 10)

        assertEquals(initialHealth - (10 + 10 / 2), defender.health)
    }

    @Test
    fun `melee fighters cannot deal damage when the defender is out of its range`() {
        val meleeAttacker = meleeFighter(1)
        val defender = Character(1)

        meleeAttacker.attack(defender, 5, distance = MELEE_RANGE + 1)

        assertNotDamaged(defender)
    }

    @Test
    fun `melee fighters can deal damage when the defender is within its range`() {
        val meleeAttacker = meleeFighter(1)
        val defender = Character(1)
        val initialHealth = defender.health

        meleeAttacker.attack(defender, 5, distance = MELEE_RANGE - 1)

        assertEquals(initialHealth - 5, defender.health)
    }

    @Test
    fun `ranged fighters cannot deal damage when the defender is out of its range`() {
        val rangedAttacker = rangedFighter(1)
        val defender = Character(1)

        rangedAttacker.attack(defender, 5, distance = RANGED_RANGE + 1)

        assertNotDamaged(defender)
    }

    @Test
    fun `ranged fighters can deal damage when the defender is within its range`() {
        val rangedAttacker = rangedFighter(1)
        val defender = Character(1)
        val initialHealth = defender.health

        rangedAttacker.attack(defender, 5, distance = RANGED_RANGE - 1)

        assertEquals(initialHealth - 5, defender.health)
    }

    @Test
    fun `characters belonging to the same faction cannot deal damage to each other`() {
        val firstCharacter = Character()
        val secondCharacter = Character()

        firstCharacter.join(Dothraki)
        secondCharacter.join(Dothraki)

        firstCharacter.attack(secondCharacter, 100)
        assertNotDamaged(secondCharacter)

        secondCharacter.attack(firstCharacter, 100)
        assertNotDamaged(firstCharacter)
    }

    @Test
    fun `characters no more belonging to the same faction can deal damage to each other`() {
        val firstCharacter = Character()
        val secondCharacter = Character()

        firstCharacter.join(Dothraki)
        secondCharacter.join(Dothraki)

        secondCharacter.leave(Dothraki)

        firstCharacter.attack(secondCharacter, 100)
        assertEquals(MAX_HEALTH - 100, secondCharacter.health)

        secondCharacter.attack(firstCharacter, 100)
        assertEquals(MAX_HEALTH - 100, firstCharacter.health)
    }

    @Test
    fun `character belonging to the same faction can heal each other`() {
        val firstCharacter = Character()
        val secondCharacter = Character()

        firstCharacter.join(Dothraki)
        secondCharacter.join(Dothraki)

        dealDamage(firstCharacter, 150)
        dealDamage(secondCharacter, 200)

        firstCharacter.heal(secondCharacter, SOME_HEALING)
        assertEquals(MAX_HEALTH - 200 + SOME_HEALING, secondCharacter.health)

        secondCharacter.heal(firstCharacter, SOME_HEALING)
        assertEquals(MAX_HEALTH - 150 + SOME_HEALING, firstCharacter.health)
    }

    @Test
    fun `character not belonging to the same faction cannot heal each other`() {
        val firstCharacter = Character()
        val secondCharacter = Character()

        firstCharacter.join(Stark)
        secondCharacter.join(Dothraki)

        dealDamage(firstCharacter, 150)

        secondCharacter.heal(firstCharacter, SOME_HEALING)
        assertEquals(MAX_HEALTH - 150, firstCharacter.health)
    }

    @Test
    fun `character can deal damage to a thing`() {
        val thing = Thing(2000)
        val character = Character()
        character.attack(thing, 10)
        assertEquals(2000 - 10, thing.health)
    }

    private fun assertNotDamaged(character: Character) {
        assertEquals(MAX_HEALTH, character.health)
    }

    private fun kill(character: Character) {
        dealDamage(character, MAX_HEALTH)
    }

    private fun dealDamage(character: Character, damage: Int) {
        Character().attack(character, damage)
    }

    companion object {
        const val SOME_HEALING = 100
    }
}
