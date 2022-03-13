package com.starter.kopring

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Test {

    @Test
    internal fun name() {
        val c = MyCustom("aa", "aaaaaa")
        val c2 = MyCustom("cc")

        assertEquals("bb", c2.v2)
    }
}

class MyCustom(val v: String?, val v2: String = "bb")

