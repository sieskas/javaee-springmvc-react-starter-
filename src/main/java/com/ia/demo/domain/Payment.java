package com.ia.demo.domain;

import com.ia.demo.domain.enums.PaymentType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Transaction transaction;

    @Column(name = "payment_type_id", nullable = false)
    private Integer paymentTypeId;

    @Transient
    private PaymentType paymentType;

    @Column(nullable = false)
    private BigDecimal amount;

    @PostLoad
    private void postLoad() {
        this.paymentType = PaymentType.fromId(this.paymentTypeId);
    }
}