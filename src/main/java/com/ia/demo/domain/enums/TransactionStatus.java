package com.ia.demo.domain.enums;

public enum TransactionStatus {
    PENDING(1),
    COMPLETED(2),
    FAILED(3),
    CANCELLED(4),
    REFUNDED(5),
    PARTIALLY_REFUNDED(6);

    private final Integer id;

    TransactionStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static TransactionStatus fromId(Integer id) {
        for (TransactionStatus status : TransactionStatus.values()) {
            if (status.id.equals(id)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown transaction status id: " + id);
    }
}