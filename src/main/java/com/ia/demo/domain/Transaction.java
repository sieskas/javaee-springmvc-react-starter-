package com.ia.demo.domain;

import com.ia.demo.domain.enums.TransactionStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sale_id", nullable = false)
    private Long saleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Sale sale;

    @Column(name = "transaction_datetime")
    private Date transactionDatetime;

    @Column(name = "transaction_reference")
    private String transactionReference;

    @Column(name = "status_id", nullable = false)
    private Integer statusId;

    @Transient
    private TransactionStatus status;

    @OneToMany(mappedBy = "transaction")
    private Set<Payment> payments = new HashSet<>();

    @PostLoad
    private void postLoad() {
        if (this.statusId != null) {
            this.status = TransactionStatus.fromId(this.statusId);
        }
    }
}