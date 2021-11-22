package com.example.blissapplicationchallenge.utils

import java.util.*

fun randomInt(list: Int): Int {
    val rand = Random()
    return rand.nextInt(list - 1)
}