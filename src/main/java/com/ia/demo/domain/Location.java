package com.ia.demo.domain;

import com.ia.demo.domain.enums.LocationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String label;

    @Column(name = "type_id", nullable = false)
    private Long typeId;

    @Transient
    private LocationType locationType;

    @Column(name = "parent_id")
    private Long parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Location parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Location> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Contact contact;

    @PostLoad
    void fillTransients() {
        if (typeId != null) {
            this.locationType = LocationType.fromId(typeId);
        }
    }
}