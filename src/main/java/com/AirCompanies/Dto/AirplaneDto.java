package com.AirCompanies.Dto;

import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.Airplane;
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
    String factorySerialNumber;
    AirCompany airCompanyId;
    int numberOfFlights;
    long flightDistance;
    long fuelCapacity;
    List<FlightDto> flights;

    public static List<AirplaneDto> fromToAirplane (List<Airplane> airplanes) {
        return airplanes.stream().map(AirplaneDto::fromAirplane).collect(Collectors.toList());
    }

    public static AirplaneDto fromAirplane (Airplane airplane){
        List<FlightDto> flightDtoList =  FlightDto.fromToFlight(airplane.getFlights());
        return AirplaneDto.builder()
                .id(airplane.getId())
                .name(airplane.getName())
                .factorySerialNumber(airplane.getFactorySerialNumber())
                .numberOfFlights(airplane.getNumberOfFlights())
                .flightDistance(airplane.getFlightDistance())
                .fuelCapacity(airplane.getFuelCapacity())
                .flights(flightDtoList)
                .build();
    }
}
