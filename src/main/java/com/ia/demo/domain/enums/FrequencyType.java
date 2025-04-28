package com.ia.demo.domain.enums;

public enum FrequencyType {
    HOURLY(1),
    DAILY(2),
    WEEKLY(3),
    MONTHLY(4),
    YEARLY(5);

    private final int id;

    FrequencyType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static FrequencyType fromId(int id) {
        for (FrequencyType type : FrequencyType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown frequency type id: " + id);
    }
}