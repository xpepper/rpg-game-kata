package com.xpeppers.kata

class Character {
    var health: Int = 1000

    fun receiveDamage(damage: Int) {
        health -= damage
    }
}