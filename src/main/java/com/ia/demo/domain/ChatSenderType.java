package com.ia.demo.domain;

import com.ia.demo.domain.enums.SenderTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "chat_sender_types")
@NoArgsConstructor
@AllArgsConstructor
public class ChatSenderType {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private SenderTypeEnum name;

    public ChatSenderType(SenderTypeEnum senderTypeEnum) {
        this.id = Long.valueOf(senderTypeEnum.getId());
        this.name = senderTypeEnum;
    }
}