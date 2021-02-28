package com.AirCompanies.Dto;

import com.AirCompanies.model.AirCompany;
import com.AirCompanies.model.Airplane;
import com.AirCompanies.model.Flight;
import com.AirCompanies.model.TypeCompany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AirCompanyDto {
    Long id;
    String name;
    TypeCompany typeCompany;
    List<AirplaneDto> airplanes;
    List<FlightDto> flights;
    LocalDateTime updateAt;

    public static AirCompanyDto fromAirCompany (AirCompany airCompany){
        List<AirplaneDto> airplaneDtoList =  AirplaneDto.fromToAirplane(airCompany.getAirplanes());
        List<FlightDto> flightDtoList =  FlightDto.fromToFlight(airCompany.getFlights());


        return AirCompanyDto.builder()
                .id(airCompany.getId())
                .name(airCompany.getName())
                .typeCompany(airCompany.getTypeCompany())
                .airplanes(airplaneDtoList)
                .flights(flightDtoList)
                .updateAt(airCompany.getUpdatedAt())
                .build();
    }
}
