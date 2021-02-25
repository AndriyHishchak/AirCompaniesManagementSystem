package com.AirCompanies.model;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "flight")
public class Flight extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status")
    Status status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airCompany_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    AirCompany airCompany;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airplane_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Airplane airplane;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "departure_country")
    Country departureCountry;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "destination_country")
    Country destinationCountry;

    @NotNull
    @Column(name = "distance")
    long distance;

    @NotNull
    @Column(name = "estimated_flight_time")
    LocalTime estimatedFlightTime;

    @NotNull
    @Column(name = "ended_at")
    Date endedAt;

    @NotNull
    @Column(name = "departure_at")
    Date departureAt;

}
