package com.ia.demo.domain.enums;

public enum ScheduleType {
    STAFF(1),
    DEVICE(2),
    MAINTENANCE(3);

    private final int id;

    ScheduleType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ScheduleType fromId(int id) {
        for (ScheduleType type : ScheduleType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown schedule type id: " + id);
    }
}
