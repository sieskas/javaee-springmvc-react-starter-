package com.ia.demo.domain.enums;

public enum LocationType {
    ROOT(1L),
    COUNTRY(2L),
    REGION(3L),
    DISTRICT(4L),
    STORE(5L);

    private final Long id;

    LocationType(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static LocationType fromId(Long id) {
        for (LocationType type : LocationType.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown location type id: " + id);
    }
}