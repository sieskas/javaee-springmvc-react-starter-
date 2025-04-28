package com.ia.demo.domain.enums;

public enum RoleEnum {

    ROLE_ADMIN(1L),
    ROLE_USER(2L);

    private final Long id;

    RoleEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static RoleEnum fromId(Long id) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.id.equals(id)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role id: " + id);
    }

    public static RoleEnum fromName(String name) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.name().equals(name)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role name: " + name);
    }
}