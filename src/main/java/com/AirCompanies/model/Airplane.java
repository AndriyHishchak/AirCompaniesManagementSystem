package com.AirCompanies.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIdentityReference(alwaysAsId=true)
public class Airplane extends BaseEntity{
    @NotNull
    @Column(name = "name")
    String name;

    @NotNull
    @Column(name = "factory_serial_number_uuid")
    String factorySerialNumber = UUID.randomUUID().toString();


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airCompany_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    AirCompany airCompany;

    @NotNull
    @Column(name = "number_of_flights")
    Integer numberOfFlights;

    @NotNull
    @Column(name = "flight_distance")
    Double flightDistance;

    @NotNull
    @Column(name = "fuel_capacity")
    Double fuelCapacity;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    TypeAirplane typeAirplane;


    @OneToMany(mappedBy = "airplane")
    List<Flight> flights;

    @Override
    public String toString() {
        return "Airplane{" +
                "name='" + name + '\'' +
                ", factorySerialNumber='" + factorySerialNumber + '\'' +
                ", airCompany=" + airCompany +
                ", numberOfFlights=" + numberOfFlights +
                ", flightDistance=" + flightDistance +
                ", fuelCapacity=" + fuelCapacity +
                ", typeAirplane=" + typeAirplane +
                '}';
    }
}
