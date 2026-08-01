package com.chick;

import java.util.HashMap;
import java.util.Map;

public class StateManager {
    private static Map<String, Boolean> birdStates = new HashMap<>();

    public static boolean getCheck(String birdId) {
        return birdStates.getOrDefault(birdId, true); // Default state is true
    }

    public static void setCheck(String birdId, boolean value) {
        birdStates.put(birdId, value);
    }
}
