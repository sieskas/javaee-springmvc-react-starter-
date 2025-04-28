package com.ia.demo.domain.enums;

public enum PaymentType {
    CASH(1),
    CARD(2),
    GIFT(3);

    private final int id;

    PaymentType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static PaymentType fromId(int id) {
        for (PaymentType type : PaymentType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown payment type id: " + id);
    }
}
