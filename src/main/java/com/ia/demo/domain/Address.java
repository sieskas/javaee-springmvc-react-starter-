package com.ia.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_line", nullable = false)
    private String addressLine;

    private String city;
    private String province;

    @Column(name = "postal_code")
    private String postalCode;

    private String country;
    private BigDecimal latitude;
    private BigDecimal longitude;
}