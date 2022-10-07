package com.nimbly.kotlinvsjava.singleton;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("InstantiationOfUtilityClass")

public class EngineJava {

    private static EngineJava instance;

    private EngineJava() {
    }

    @NotNull
    public static EngineJava getInstance() {
        if (instance == null) {
            instance = new EngineJava();
        }
        return instance;
    }

    public void process(String param) {
    }
}
