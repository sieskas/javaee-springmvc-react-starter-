package com.ia.demo.domain;

import com.ia.demo.domain.enums.FrequencyType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "weather")
public class Weather {
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

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(name = "frequency_id", nullable = false)
    private Integer frequencyId;

    @Transient
    private FrequencyType frequency;

    private BigDecimal temperature;
    private BigDecimal humidity;

    @Column(name = "wind_speed")
    private BigDecimal windSpeed;

    @Column(name = "weather_code")
    private String weatherCode;

    private BigDecimal precipitation;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PostLoad
    private void postLoad() {
        this.frequency = FrequencyType.fromId(this.frequencyId);
    }
}
