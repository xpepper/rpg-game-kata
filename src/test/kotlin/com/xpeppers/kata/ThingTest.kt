package com.xpeppers.kata

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ThingTest {

    @Test
    fun `damaging a thing reduce its health`() {
        val tree = Thing(2000)
        tree.receiveDamage(10)
        assertEquals(2000 - 10, tree.health)
    }

    @Test
    fun `thing is destroyed when its health goes down to 0`() {
        val thing = Thing(10)
        assertFalse(thing.isDestroyed())

        thing.receiveDamage(20)

        assertTrue(thing.isDestroyed())
        assertEquals(0, thing.health)
    }
}

