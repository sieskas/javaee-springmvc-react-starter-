package com.ia.demo.domain;

import com.ia.demo.domain.enums.RoleEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleEnum name;
}