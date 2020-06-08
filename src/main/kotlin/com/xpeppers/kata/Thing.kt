package com.xpeppers.kata

class Thing(private val _health: Int) {

    var health: Int = _health
    private set

    fun receiveDamage(damage: Int) {
        health -= damage
    }
}