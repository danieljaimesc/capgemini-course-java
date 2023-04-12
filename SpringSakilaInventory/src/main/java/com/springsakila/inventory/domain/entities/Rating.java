package com.springsakila.inventory.domain.entities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Rating {
    GENERAL_AUDIENCES("G"),
    PARENTAL_GUIDANCE_SUGGESTED("PG"),
    PARENTS_STRONGLY_CAUTIONED("PG-13"),
    RESTRICTED("R"),
    ADULTS_ONLY("NC-17");

    public static final Map<String, Rating> VALID_VALUES = new HashMap<>();

    static {
        Arrays.stream(Rating.values()).forEach(item -> VALID_VALUES.put(item.value, item));
    }

    private final String value;

    Rating(String value) {
        this.value = value;
    }

    public static Rating getEnum(String value) {
        if (!VALID_VALUES.containsKey(value)) throw new IllegalArgumentException("Unexpected value: " + value);
        return VALID_VALUES.get(value);
    }

    public String getValue() {
        return value;
    }
}
