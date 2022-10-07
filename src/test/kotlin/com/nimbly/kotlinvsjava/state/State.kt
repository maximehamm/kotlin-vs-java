package com.nimbly.kotlinvsjava.state

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class State {

    @Test
    fun testK() {
        val sender = MailSender("DEFAULT")
        sendBothMail(sender, "ME", "HIM");
        assertEquals( "DEFAULT", sender.from);
    }
}

fun sendBothMail(sender: MailSender, extra1: String, extra2: String) {

    // Send using default
    sender.send("Hello", "I love Kotlin")

    // Send using extra1
    sender.from(extra1) {
        send("Hello", "He loves Kotlin")
    }

    // Send using extra2
    sender.from(extra2) {
        send("Hello", "He loves Kotlin")
    }
}

private fun MailSender.from(from: String, block: MailSender.() -> Unit) {
    val saved = this.from
    try {
        this.from = from
        block()
    } finally {
        this.from = saved
    }
}

class MailSender(var from: String) {
    fun send(subject: String, body: String) {
        println("Sending mail from $from");
    }
}