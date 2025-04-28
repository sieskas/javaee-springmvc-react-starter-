package com.ia.demo.domain.enums;

public enum StaffHoursSource {
    SCHEDULE(1),
    POS(2),
    MANUAL(3);

    private final int id;

    StaffHoursSource(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static StaffHoursSource fromId(int id) {
        for (StaffHoursSource source : StaffHoursSource.values()) {
            if (source.id == id) {
                return source;
            }
        }
        throw new IllegalArgumentException("Unknown staff hours source id: " + id);
    }
}