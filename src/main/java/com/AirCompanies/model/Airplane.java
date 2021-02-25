package com.AirCompanies.model;

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
    @Column(name = "name")
    String name;
    @Column(name = "factory_serial_number_uuid")
    String factorySerialNumber = UUID.randomUUID().toString();
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airCompany_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    AirCompany airCompanyId;
    @Column(name = "number_of_flights")
    int numberOfFlights;
    @Column(name = "flight_distance")
    long flightDistance;
    @Column(name = "fuel_capacity")
    long fuelCapacity;
    @Column(name = "type")
    Type type;
    @OneToMany(mappedBy = "airplane")
    List<Flight> flights;


}
