package com.nimbly.kotlinvsjava.stream

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StreamKotlinTest {

    @Test
    fun testK() {

        val usersMap =
            UsersDB.allUsers()
                .map { it.id to it }
                .toMap()

        assertEquals(usersMap.size, 5)
        assertEquals(usersMap["U003"]?.firstName, "Maurine")

    }
}
object UsersDB {

    fun allUsers()
        = listOf(
            User("U001", "Maxime", "HAMM"),
            User("U002", "Muriel", "HAMM"),
            User("U003", "Maurine", "HAMM"),
            User("U004", "Loïse", "HAMM"),
            User("U005", "Romy", "HAMM"))
}

class User(
    val id: String,
    val firstName: String,
    val lastName: String
)