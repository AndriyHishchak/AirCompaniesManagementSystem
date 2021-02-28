package com.AirCompanies.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "flight")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIdentityReference(alwaysAsId=true)
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
    Double distance;

    @NotNull
    @Column(name = "estimated_flightTime", columnDefinition = "TIME")
    LocalTime estimatedFlightTime;


    @Column(name = "ended_at", columnDefinition = "TIMESTAMP")
    LocalDateTime  endedAt;


    @Column(name = "started_at", columnDefinition = "TIMESTAMP")
    LocalDateTime startedAt;


    @Column(name = "delay_start_at", columnDefinition = "TIMESTAMP")
    LocalDateTime delayStartAt;

    @Override
    public String toString() {
        return "Flight{" +
                "status=" + status +
                ", airCompany=" + airCompany +
                ", airplane=" + airplane +
                ", departureCountry=" + departureCountry +
                ", destinationCountry=" + destinationCountry +
                ", distance=" + distance +
                ", estimatedFlightTime=" + estimatedFlightTime +
                ", endedAt=" + endedAt +
                ", StartedAt=" + startedAt +
                '}';
    }
}
