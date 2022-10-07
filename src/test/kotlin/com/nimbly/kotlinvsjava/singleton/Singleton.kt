@file:Suppress("unused", "UNUSED_PARAMETER")

package com.nimbly.kotlinvsjava.singleton

import org.junit.jupiter.api.Test

class Singleton {

    @Test
    fun singleton() {

        val engineKotlin = EngineKotlin
        engineKotlin.process("Lets do it")

        val engineJava = EngineJava.getInstance();
        engineJava.process("Lets do it")
    }
}

object EngineKotlin {
    fun process(param: String) {
    }
}