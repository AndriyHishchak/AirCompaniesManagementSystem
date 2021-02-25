package com.AirCompanies.model;

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
    @JoinColumn(name = "status")
    Status status;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airCompany_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    AirCompany airCompanyId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airplane_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Airplane airplaneId;
    @JoinColumn(name = "departure_country")
    Country departureCountry;
    @JoinColumn(name = "destination_country")
    Country destinationCountry;
    @JoinColumn(name = "distance")
    long distance;
    @JoinColumn(name = "estimated_flight_time")
    LocalTime estimatedFlightTime;
    @JoinColumn(name = "ended_at")
    Date endedAt;
    @JoinColumn(name = "delay_started_at")
    Date delayStartedAt;

}
