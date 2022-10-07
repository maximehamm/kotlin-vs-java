package com.nimbly.kotlinvsjava.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailSenderJavaTest {

    @Test
    public void testJ() {
        MailSender sender = new MailSender("DEFAULT");
        sendBothMailJava(sender, "ME", "HIM");
        assertEquals( "DEFAULT", sender.getFrom());
    }

    public static void sendBothMailJava(MailSender sender, String extra1, String extra2) {

        // Send using default
        sender.send("Hello", "I love Kotlin");

        // Send using extra
        String saved = sender.getFrom();
        try {
            sender.setFrom(extra1);
            sender.send("Hello", "He loves Kotlin");

            sender.setFrom(extra2);
            sender.send("Hello", "He loves Kotlin");
        } finally {
            sender.setFrom(saved);
        }
    }
}
