package com.miho.spring5mvcrest.utils

import com.fasterxml.jackson.databind.ObjectMapper

fun Any.asJsonString(): String {
    try {
        return ObjectMapper().writeValueAsString(this) ?: throw IllegalArgumentException("Dafuq dude")
    } catch (e: Exception) {
        throw IllegalArgumentException("Now u done fucked up, kid", e)
    }
}