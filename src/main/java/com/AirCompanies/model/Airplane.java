package com.AirCompanies.model;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "airplane")
public class Airplane extends BaseEntity{
    @NotNull
    @Column(name = "name")
    String name;

    @NotNull
    @Column(name = "factory_serial_number_uuid")
    String factorySerialNumber = UUID.randomUUID().toString();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airCompany_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    AirCompany airCompany;

    @NotNull
    @Column(name = "number_of_flights")
    int numberOfFlights;

    @NotNull
    @Column(name = "flight_distance")
    long flightDistance;

    @NotNull
    @Column(name = "fuel_capacity")
    long fuelCapacity;

    @NotNull
    @Column(name = "type")
    TypeAirplane typeAirplane;

    @OneToMany(mappedBy = "airplane")
    List<Flight> flights;


}
