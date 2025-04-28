package com.ia.demo.domain;

import com.ia.demo.domain.enums.SenderTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@Table(name = "chat_messages")
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ChatSession session;

    @ManyToOne
    @JoinColumn(name = "sender_type_id", nullable = false)
    private ChatSenderType senderType;


    private String content;
    private LocalDateTime timestamp = LocalDateTime.now();

//     public SenderTypeEnum getSenderTypeEnum() {
//         return senderType.getName();
//     }
}
