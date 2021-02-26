package com.AirCompanies.Dto;

import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.Airplane;
import com.AirCompanies.model.TypeAirplane;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AirplaneDto {
    long id;
    String name;
    TypeAirplane typeAirplane;
    String factorySerialNumber;
    AirCompany airCompany;
    int numberOfFlights;
    Double flightDistance;
    Double fuelCapacity;
    List<FlightDto> flights;


    public static List<AirplaneDto> fromToAirplane (List<Airplane> airplanes) {
        return airplanes.stream().map(AirplaneDto::fromAirplane).collect(Collectors.toList());
    }

    public static AirplaneDto fromAirplane (Airplane airplane){
        List<FlightDto> flightDtoList =  FlightDto.fromToFlight(airplane.getFlights());

        return AirplaneDto.builder()
                .id(airplane.getId())
                .name(airplane.getName())
                .typeAirplane(airplane.getTypeAirplane())
                .factorySerialNumber(airplane.getFactorySerialNumber())
                .airCompany(airplane.getAirCompany())
                .numberOfFlights(airplane.getNumberOfFlights())
                .flightDistance(airplane.getFlightDistance())
                .fuelCapacity(airplane.getFuelCapacity())
                .flights(flightDtoList)
                .build();
    }
}
