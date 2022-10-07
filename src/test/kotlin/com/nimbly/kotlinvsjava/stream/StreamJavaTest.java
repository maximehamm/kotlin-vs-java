package com.nimbly.kotlinvsjava.stream;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamJavaTest {

    @Test
    public void testJ() {

        Map<String, User> usersMap =
            UsersDB.INSTANCE.allUsers()
                .stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));

        assertEquals(usersMap.size(), 5);
        assertEquals(usersMap.get("U003").getFirstName(), "Maurine");
    }
}
