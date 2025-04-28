package com.ia.demo.domain;

import com.ia.demo.domain.enums.StaffHoursSource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "location_staff_hours")
public class LocationStaffHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location_id", nullable = false)
    private Long locationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Location location;

    @Column(name = "work_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date workDate;

    @Column(name = "source_id", nullable = false)
    private Integer sourceId;

    @Transient
    private StaffHoursSource source;

    @Column(name = "total_hours", nullable = false)
    private BigDecimal totalHours;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PostLoad
    private void postLoad() {
        this.source = StaffHoursSource.fromId(this.sourceId);
    }
}