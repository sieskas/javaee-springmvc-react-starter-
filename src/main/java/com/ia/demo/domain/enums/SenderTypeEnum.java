package com.ia.demo.domain.enums;

import lombok.Getter;

@Getter
public enum SenderTypeEnum {
    USER(1),
    BOT(2);

    private final Integer id;

    SenderTypeEnum(Integer id) {
        this.id = id;
    }

    public static SenderTypeEnum fromId(Integer id) {
        for (SenderTypeEnum status : SenderTypeEnum.values()) {
            if (status.id.equals(id)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown SenderTypeEnum status id: " + id);
    }
}
